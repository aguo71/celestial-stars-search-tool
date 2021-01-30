package edu.brown.cs.student.stars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Class to represent naive_neighbor repl command and behavior.
 */
public class NaiveNeighborCommand implements Action {
  private ArrayList<Star> stars;
  // HashMap mapping distance to each star from dataset stars based on the input star/coordinate
  private HashMap<Double, ArrayList<Star>> neighbors;

  /**
   *
   * @param stars dataset of stars to perform naive_neighbor command on
   */
  public NaiveNeighborCommand(ArrayList<Star> stars) {
    this.stars = stars;
  }

  /**
   * Finds the closest n neighbors using global neighbors Hashmap.
   * @param n number of neighbors to find and return
   * @return list of n closest stars
   */
  private ArrayList<Star> getNNeighbors(Integer n) {
    ArrayList<Star> closest = new ArrayList<>();
    // Sorts all existing stars by increasing distance using hashmap neighbors,
    // and adds the first n stars to a list
    ArrayList<Double> distances = new ArrayList<>(neighbors.keySet());
    Collections.sort(distances);
    ArrayList<Star> nearbyStars;
    int counter = 0;
    for (Double distance : distances) {
      if (counter == n) {
        break;
      }
      nearbyStars = neighbors.get(distance);
      counter += nearbyStars.size();
      if (counter > n) {
        // Randomly shuffles tied stars in the case of a tie that puts the total star count above n
        Collections.shuffle(nearbyStars);
        int needed = n - (counter - nearbyStars.size());
        for (int i = 0; i < needed; i++) {
          closest.add(nearbyStars.get(i));
        }
      } else {
        // Adds star to eventually returned list
        Collections.shuffle(nearbyStars);
        closest.addAll(nearbyStars);
      }
    }
    return closest;
  }

  @Override
  public void run(String[] args) {
    if (args.length != 5 && args.length != 3) {
      System.out.println("ERROR: incorrect number of arguments for naive_neighbors method");
      return;
    }

    if (stars.size() == 0) {
      System.out.println("ERROR: No star data exists");
      return;
    }

    if (Integer.parseInt(args[1]) < 0) {
      System.out.println("ERROR: Neighbor count must be non-negative");
      return;
    }
    // Calculates distance of each star in dataset stars from input coordinate or star name
    NeighborDistances calculator = new NeighborDistances(stars);
    if (args.length == 5) {
      // Case when command input was coordinate
      neighbors = calculator.findDistances(new Coordinates(Double.parseDouble(args[2]),
          Double.parseDouble(args[3]), Double.parseDouble(args[4])));
      // Finds and prints the appropriate number of neighboring stars
      ArrayList<Star> closest = getNNeighbors(Integer.parseInt(args[1]));
      for (Star star : closest) {
        System.out.println(star.getID());
      }
    } else {
      // Case when command input was star name
      String toFind = args[2].replaceAll("\"", "");
      Coordinates coord = null;
      Star toRemove = null;
      // Keeps track of input star so we can remove it from final printed list
      for (Star star : stars) {
        if (star.getName().equals(toFind)) {
          coord = star.getCoordinates();
          toRemove = star;
          break;
        }
      }

      if (coord == null) {
        System.out.println("ERROR: Star not found");
        return;
      }

      neighbors = calculator.findDistances(coord);
      // Finds and prints the appropriate number of neighboring stars except star initially
      // mentioned in repl command
      ArrayList<Star> closest = getNNeighbors(Integer.parseInt(args[1]) + 1);
      closest.remove(toRemove);
      for (Star star : closest) {
        System.out.println(star.getID());
      }
    }
  }
}
