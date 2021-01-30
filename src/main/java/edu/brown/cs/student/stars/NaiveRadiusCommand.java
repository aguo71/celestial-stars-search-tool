package edu.brown.cs.student.stars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Class representing naive_radius command and behavior.
 */
public class NaiveRadiusCommand implements Action {
  private ArrayList<Star> stars;
  // HashMap mapping distance to each star from dataset stars based on the input star/coordinate
  private HashMap<Double, ArrayList<Star>> neighbors;

  /**
   *
   * @param stars dataset of stars to perform naive_radius command on
   */
  public NaiveRadiusCommand(ArrayList<Star> stars) {
    this.stars = stars;
  }

  /**
   * Finds all neighboring stars within input radius distance in dataset stars.
   * @param radius maximum distance of stars we want to find
   * @return list of stars within radius distance using hashmap neighbors
   */
  private ArrayList<Star> getNeighborsInRadius(Double radius) {
    ArrayList<Star> inRange = new ArrayList<>();
    // Sorts all existing stars by increasing distance using hashmap neighbors, and adds each star
    // within radius distance to a list
    ArrayList<Double> distances = new ArrayList<>(neighbors.keySet());
    Collections.sort(distances);
    ArrayList<Star> nearbyStars;
    for (Double distance : distances) {
      if (distance > radius) {
        break;
      }
      nearbyStars = neighbors.get(distance);
      Collections.shuffle(nearbyStars);
      inRange.addAll(nearbyStars);
    }
    return inRange;
  }

  @Override
  public void run(String[] args) {
    if (args.length != 5 && args.length != 3) {
      System.out.println("ERROR: incorrect number of arguments for naive_radius method");
      return;
    }

    if (stars.size() == 0) {
      System.out.println("ERROR: No star data exists");
      return;
    }

    if (Double.parseDouble(args[1]) < 0) {
      System.out.println("ERROR: Radius must be non-negative");
      return;
    }
    // Calculates distance of each star in dataset stars from input coordinate or star name
    NeighborDistances calculator = new NeighborDistances(stars);
    if (args.length == 5) {
      // Case when coordinate was specified in input
      neighbors = calculator.findDistances(new Coordinates(Double.parseDouble(args[2]),
          Double.parseDouble(args[3]), Double.parseDouble(args[4])));
      // Finds and prints all stars within input radius
      ArrayList<Star> closest = getNeighborsInRadius(Double.parseDouble(args[1]));
      for (Star star : closest) {
        System.out.println(star.getID());
      }
    } else {
      // Case when star name was specified in input
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
      // Finds and prints all stars within input radius except star initially mentioned in repl
      // command
      ArrayList<Star> closest = getNeighborsInRadius(Double.parseDouble(args[1]));
      closest.remove(toRemove);
      for (Star star : closest) {
        System.out.println(star.getID());
      }
    }
  }
}
