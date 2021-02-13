package edu.brown.cs.student.stars;

import tools.Action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Class to represent naive_neighbor repl command and behavior.
 */
public class NaiveNeighborCommand implements Action {
  private List<Star> stars;
  // HashMap mapping distance to each star from dataset stars based on the input star/coordinate
  private Map<Double, List<Star>> neighbors;
  private List<Star> closest = new ArrayList<>();

  /**
   *
   * @param stars dataset of stars to perform naive_neighbor command on
   */
  public NaiveNeighborCommand(List<Star> stars) {
    this.stars = stars;
  }

  /**
   * Finds the closest n neighbors using global neighbors Hashmap.
   * @param n number of neighbors to find and return
   * @return list of n closest stars
   */
  private void getNNeighbors(Integer n) {
    closest.clear();
    // Sorts all existing stars by increasing distance using hashmap neighbors,
    // and adds the first n stars to a list
    List<Double> distances = new ArrayList<>(neighbors.keySet());
    Collections.sort(distances);
    List<Star> nearbyStars;
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
  }

  @Override
  public String run(String[] args) {
    if (args.length != 5 && args.length != 3) {
      System.out.println("ERROR: incorrect number of arguments for naive_neighbors method");
      return "ERROR: incorrect number of arguments for naive_neighbors method";
    }
    if (stars.isEmpty()) {
      return "";
    }
    if (stars.get(0).getName() == null) {
      System.out.println("ERROR: No prior call of stars");
      return "ERROR: No prior call of stars";
    }

    int neighborCount;
    try {
      neighborCount = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      System.out.println("ERROR: Neighbor count must be an integer");
      return "ERROR: Neighbor count must be an integer";
    }

    if (neighborCount < 0) {
      System.out.println("ERROR: Neighbor count must be non-negative");
      return "ERROR: Neighbor count must be non-negative";
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
        return "ERROR: Input coordinates not numbers";
      }
      neighbors = calculator.findDistances(coords);
      // Finds and prints the appropriate number of neighboring stars
      getNNeighbors(neighborCount);
      for (Star star : closest) {
        System.out.println(star.getID());
      }
    } else {
      // Case when command input was star name
      String toFind = args[2].replaceAll("\"", "");
      if (args[2].equals("\"\"")) {
        System.out.println("ERROR: Star name cannot be empty string");
        return "ERROR: Star name cannot be empty string";
      }
      List<Double> coords = new ArrayList<>();
      Star toRemove = null;
      // Keeps track of input star so we can remove it from final printed list
      for (Star star : stars) {
        if (star.getName().equals(toFind)) {
          coords = star.getCoordinates();
          toRemove = star;
          break;
        }
      }

      if (toRemove == null) {
        System.out.println("ERROR: Star not found");
        return "ERROR: Star not found";
      }

      neighbors = calculator.findDistances(coords);
      // Finds and prints the appropriate number of neighboring stars except star initially
      // mentioned in repl command
      getNNeighbors(neighborCount + 1);
      for (Star star : closest) {
        if (!star.getName().equals(toRemove.getName())) {
          System.out.println(star.getID());
        }
      }
    }
    return "";
  }

  /**
   * Getter for MBT testing.
   * @return list of nearest neighbors
   */
  public List<Star> getResults() {
    return this.closest;
  }
}
