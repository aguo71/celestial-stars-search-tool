package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Class holding function to find neighbors in radius of a coordinate.
 * @param <T>
 */
public class NeighborsInRadius<T extends HasCoordinates> {

  private List<Double> target;
  // stores distance from target coordinate -> list of stars at that distance away
  private TreeMap<Double, List<T>> neighbors;
  private double radius;
  private DistanceCalculator calc = new DistanceCalculator();

  /**
   * Constructor.
   * @param neighbors Treemap to populate with neighbors
   * @param target target coordinate
   * @param radius radius around target to find T objects within
   */
  public NeighborsInRadius(TreeMap<Double, List<T>> neighbors, List<Double> target, double radius) {
    this.neighbors = neighbors;
    this.target = target;
    this.radius = radius;
  }

  /**
   * Populates treemap recursively by traversing input KDtree.
   * @param kdtree KDTree of objects to search along
   */
  public void run(KDTree<T> kdtree) {
    if (kdtree == null) {
      return;
    }

    double dist = calc.getDistance(kdtree.getVal().getCoordinates(), target);
    if (neighbors.isEmpty() && dist <= radius) {
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
    double axisDist = Math.abs(kdtree.getVal().getCoordinate(axis) - target.get(axis));
    if (axisDist <= radius) {
      run(kdtree.getLeft());
      run(kdtree.getRight());
    } else {
      if (kdtree.getVal().getCoordinate(axis) < target.get(axis)) {
        run(kdtree.getRight());
      } else {
        run(kdtree.getLeft());
      }
    }






  }


}
