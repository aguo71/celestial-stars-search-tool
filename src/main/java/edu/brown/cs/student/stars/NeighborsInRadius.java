package edu.brown.cs.student.stars;

import tools.DistanceCalculator;
import tools.HasCoordinates;
import tools.KDTree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class NeighborsInRadius<T extends HasCoordinates> {

  private List<Double> target;
  // stores distance from target coordinate -> list of stars at that distance away
  private TreeMap<Double, List<T>> neighbors;
  private double radius;
  DistanceCalculator calc = new DistanceCalculator();

  public NeighborsInRadius (TreeMap<Double, List<T>> neighbors, List<Double> target, double radius){
    this.neighbors = neighbors;
    this.target = target;
    this.radius = radius;
  }

  public void run(KDTree<T> kdtree) {
    if (kdtree == null) {
      return;
    }

    double dist = calc.getDistance(kdtree.getVal().getCoordinates(), target);
    if(neighbors.isEmpty() && dist <= radius) {
      List<T> lst = new ArrayList<>();
      lst.add(kdtree.getVal());
      neighbors.put(dist, lst);
    } else if (neighbors.containsKey(dist)) {
      List<T> lst = neighbors.get(dist);
      lst.add(kdtree.getVal());
    } else if (dist <= radius) {
      List<T> lst = new ArrayList<>();
      lst.add(kdtree.getVal());
      neighbors.put(dist, lst);
    }

    int axis = kdtree.getDepth() % kdtree.getVal().numDimensions();
    if(dist <= radius) {
      run(kdtree.getLeft());
      run(kdtree.getRight());
    } else {
      if(kdtree.getVal().getCoordinate(axis) < target.get(axis)) {
        run(kdtree.getRight());
      } else {
        run(kdtree.getLeft());
      }
    }






  }


}
