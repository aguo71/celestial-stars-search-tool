package edu.brown.cs.student.stars;

import tools.Action;
import tools.KDTree;
import tools.NeighborsInRadius;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * KDTree non-naive implementation of stars radius repl command.
 */
public class RadiusCommand implements Action {
  private KDTree<Star> starTree;
  private TreeMap<Double, List<Star>> neighbors = new TreeMap<>();
  private List<Star> stars;

  /**
   *
   * @param starTree KDTree of stars
   * @param stars List of stars
   */
  public RadiusCommand(KDTree<Star> starTree, List<Star> stars) {
    this.starTree = starTree;
    this.stars = stars;
  }

  @Override
  public String run(String[] args) {
    neighbors = new TreeMap<>();
    if (args.length != 5 && args.length != 3) {
      System.out.println("ERROR: incorrect number of arguments for radius method");
      return "ERROR: incorrect number of arguments for radius method";
    }
    if (starTree.getDepth() == -1) {
      System.out.println("ERROR: No prior call of stars");
      return "ERROR: No prior call of stars";
    }
    if (starTree.getVal() == null) {
      return "";
    }
    double radius;
    try {
      radius = Double.parseDouble(args[1]);
    } catch (NumberFormatException e) {
      System.out.println("ERROR: Radius must be a double");
      return "ERROR: Radius must be a double";
    }

    if (radius < 0) {
      System.out.println("ERROR: Radius must be non-negative");
      return "ERROR: Radius must be non-negative";
    }

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
      // Finds and prints the appropriate number of neighboring stars
      NeighborsInRadius<Star> algorithm = new NeighborsInRadius<>(neighbors, coords, radius);
      algorithm.run(starTree);
      // Prints ID of each star in neighbors
      for (Double distance : neighbors.keySet()) {
        Collections.shuffle(neighbors.get(distance));
        for (Star star : neighbors.get(distance)) {
          System.out.println(star.getID());
        }
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
      // Finds and prints the appropriate number of neighboring stars
      NeighborsInRadius<Star> algorithm = new NeighborsInRadius<>(neighbors, coords, radius);
      algorithm.run(starTree);
      // Prints ID of each star in neighbors
      for (Double distance : neighbors.keySet()) {
        Collections.shuffle(neighbors.get(distance));
        for (Star star : neighbors.get(distance)) {
          if (!star.getName().equals(toRemove.getName())) {
            System.out.println(star.getID());
          }
        }
      }
    }
    return "";
  }

  /**
   * Getter for MBT testing.
   * @return TreeMap of nearest neighbors
   */
  public TreeMap<Double, List<Star>> getResults() {
    return this.neighbors;
  }
}
