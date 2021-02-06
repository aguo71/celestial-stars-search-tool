package edu.brown.cs.student.stars;

import tools.Action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.List;

/**
 * Class representing naive_radius command and behavior.
 */
public class NaiveRadiusCommand implements Action {
  private List<Star> stars;
  // HashMap mapping distance to each star from dataset stars based on the input star/coordinate
  private Map<Double, List<Star>> neighbors;

  /**
   *
   * @param stars dataset of stars to perform naive_radius command on
   */
  public NaiveRadiusCommand(List<Star> stars) {
    this.stars = stars;
  }

  /**
   * Finds all neighboring stars within input radius distance in dataset stars.
   * @param radius maximum distance of stars we want to find
   * @return list of stars within radius distance using hashmap neighbors
   */
  private List<Star> getNeighborsInRadius(Double radius) {
    List<Star> inRange = new ArrayList<>();
    // Sorts all existing stars by increasing distance using hashmap neighbors, and adds each star
    // within radius distance to a list
    List<Double> distances = new ArrayList<>(neighbors.keySet());
    Collections.sort(distances);
    List<Star> nearbyStars;
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
      List<Double> coords = new ArrayList<>();
      try {
        coords.add(Double.parseDouble(args[2]));
        coords.add(Double.parseDouble(args[3]));
        coords.add(Double.parseDouble(args[4]));
      } catch (NumberFormatException e) {
        System.out.println("ERROR: Input coordinates not numbers");
        return;
      }
      neighbors = calculator.findDistances(coords);
      // Finds and prints all stars within input radius
      List<Star> closest = getNeighborsInRadius(Double.parseDouble(args[1]));
      for (Star star : closest) {
        System.out.println(star.getID());
      }
    } else {
      // Case when star name was specified in input
      String toFind = args[2].replaceAll("\"", "");
      if (args[2].equals("\"\"")) {
        System.out.println("ERROR: Star name cannot be empty string");
        return;
      }
      Star toRemove = null;
      List<Double> coords = new ArrayList<>();
      // Keeps track of input star so we can remove it from final printed list
      for (Star star : stars) {
        if (star.getName().equals(toFind)) {
          toRemove = star;
          coords = star.getCoordinates();
          break;
        }
      }

      if (toRemove == null) {
        System.out.println("ERROR: Star not found");
        return;
      }
      neighbors = calculator.findDistances(coords);
      // Finds and prints all stars within input radius except star initially mentioned in repl
      // command
      List<Star> closest = getNeighborsInRadius(Double.parseDouble(args[1]));
      closest.remove(toRemove);
      for (Star star : closest) {
        System.out.println(star.getID());
      }
    }
  }
}
