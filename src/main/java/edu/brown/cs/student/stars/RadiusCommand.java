package edu.brown.cs.student.stars;

import tools.Action;
import tools.GetKNeighbors;
import tools.KDTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class RadiusCommand implements Action {
  private KDTree<Star> starTree;
  private TreeMap<Double, List<Star>> neighbors = new TreeMap<>();
  private List<Star> stars;

  public RadiusCommand(KDTree<Star> starTree, List<Star> stars) {
    this.starTree = starTree;
    this.stars = stars;
  }

  @Override
  public void run(String[] args) {
    if (args.length != 5 && args.length != 3) {
      System.out.println("ERROR: incorrect number of arguments for radius method");
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

    if (args.length == 5) {
      // Case when coordinate was specified in input
      List<Double> coords = new ArrayList<>();
      coords.add(Double.parseDouble(args[2]));
      coords.add(Double.parseDouble(args[3]));
      coords.add(Double.parseDouble(args[4]));
      double r = Double.parseDouble(args[1]);
      // Finds and prints the appropriate number of neighboring stars
      NeighborsInRadius<Star> algorithm = new NeighborsInRadius<>(neighbors, coords, r);
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
        return;
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
        return;
      }
      double r = Double.parseDouble(args[1]);
      // Finds and prints the appropriate number of neighboring stars
      NeighborsInRadius<Star> algorithm = new NeighborsInRadius<>(neighbors, coords, r);
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

  }
}
