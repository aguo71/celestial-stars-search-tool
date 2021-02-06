package edu.brown.cs.student.stars;

import tools.Action;
import tools.GetKNeighbors;
import tools.KDTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * KDTree non-naive implementation of stars neighbor repl command.
 */
public class NeighborCommand implements Action {
  private KDTree<Star> starTree;
  private TreeMap<Double, List<Star>> neighbors = new TreeMap<>();
  private List<Star> stars;

  /**
   *
   * @param starTree KDTree of stars
   * @param stars List of stars
   */
  public NeighborCommand(KDTree<Star> starTree, List<Star> stars) {
    this.starTree = starTree;
    this.stars = stars;
  }

  @Override
  public void run(String[] args) {
    neighbors = new TreeMap<>();
    if (args.length != 5 && args.length != 3) {
      System.out.println("ERROR: incorrect number of arguments for neighbors command");
      return;
    }

    if (starTree.getDepth() == -1) {
      System.out.println("ERROR: No prior call of stars");
      return;
    }
    if (starTree.getVal() == null) {
      return;
    }

    int k;
    try {
      k = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      System.out.println("ERROR: Neighbor count must be an integer");
      return;
    }

    if (k < 0) {
      System.out.println("ERROR: Neighbor count must be non-negative");
      return;
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
        return;
      }
      // Finds and prints the appropriate number of neighboring stars
      GetKNeighbors<Star> algorithm = new GetKNeighbors<>(neighbors, coords, k);
      int totalStars = algorithm.run(starTree);
      // Culls appropriate number of stars from neighbors in the case of ties
      while (totalStars > k) {
        int diff = totalStars - k;
        List<Star> furthest = neighbors.get(neighbors.lastKey());
        if (furthest.size() <= diff) {
          totalStars -= furthest.size();
          neighbors.pollLastEntry();
        } else {
          Collections.shuffle(furthest);
          for (int i = 0; i < diff; i++) {
            furthest.remove(0);
          }
          totalStars -= diff;
        }
      }
      // Prints ID of each star in neighbors
      for (Double distance : neighbors.keySet()) {
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

      // Finds and prints the appropriate number of neighboring stars
      GetKNeighbors<Star> algorithm = new GetKNeighbors<>(neighbors, coords, k + 1);
      int totalStars = algorithm.run(starTree);
      // Culls appropriate number of stars from neighbors in the case of ties
      while (totalStars > k + 1) {
        int diff = totalStars - k;
        List<Star> furthest = neighbors.get(neighbors.lastKey());
        if (furthest.size() <= diff) {
          totalStars -= furthest.size();
          neighbors.pollLastEntry();
        } else {
          Collections.shuffle(furthest);
          for (int i = 0; i < diff; i++) {
            furthest.remove(0);
          }
          totalStars -= diff;
        }
      }
      // Prints ID of each star in neighbors except input star
      for (Double distance : neighbors.keySet()) {
        for (Star star : neighbors.get(distance)) {
          if (!star.getName().equals(toRemove.getName())) {
            System.out.println(star.getID());
          }
        }
      }
    }
  }

  /**
   * Getter for MBT testing.
   * @return TreeMap of nearest neighbors
   */
  public TreeMap<Double, List<Star>> getResults() {
    return this.neighbors;
  }
}
