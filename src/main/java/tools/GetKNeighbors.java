package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Class holding function to find K-nearest neighbors.
 * @param <T>
 */
public class GetKNeighbors<T extends HasCoordinates> {
  private List<Double> target;
  // stores distance from target coordinate -> list of T objects (stars) at that distance away
  private TreeMap<Double, List<T>> neighbors;
  private int k;
  private DistanceCalculator calc = new DistanceCalculator();

  /**
   * Constructor.
   * @param neighbors Empty treemap to fill with nearest neighbors
   * @param target target coordinate
   * @param k number of neighbors sought after
   */
  public GetKNeighbors(TreeMap<Double, List<T>> neighbors, List<Double> target, int k) {
    this.neighbors = neighbors;
    this.target = target;
    this.k = k;
  }

  /**
   * Finds nearest neighbors using input.
   * @param kdtree kdtree of T objects to search with
   * @return number of net neighbors added to treemap
   */
  public int run(KDTree<T> kdtree) {
    if (kdtree == null) {
      return 0;
    }
    int counter = 0;
    double dist = calc.getDistance(kdtree.getVal().getCoordinates(), target);
    if (neighbors.isEmpty()) {
      List<T> lst = new ArrayList<>();
      lst.add(kdtree.getVal());
      neighbors.put(dist, lst);
      counter += 1;
    } else if (neighbors.containsKey(dist)) {
      List<T> lst = neighbors.get(dist);
      lst.add(kdtree.getVal());
      counter += 1;
    } else if (neighbors.size() < k) {
      List<T> lst = new ArrayList<>();
      lst.add(kdtree.getVal());
      neighbors.put(dist, lst);
      counter += 1;
    } else if (dist < neighbors.lastKey()) {
      counter -= neighbors.remove(neighbors.lastKey()).size();
      List<T> lst = new ArrayList<>();
      lst.add(kdtree.getVal());
      neighbors.put(dist, lst);
      counter += 1;
    }

    int axis = kdtree.getDepth() % kdtree.getVal().numDimensions();
    double axisDist = Math.abs(kdtree.getVal().getCoordinate(axis) - target.get(axis));
    double furthestD = neighbors.lastKey();
    if (axisDist < furthestD) {
      return counter + run(kdtree.getLeft()) + run(kdtree.getRight());
    } else {
      if (kdtree.getVal().getCoordinate(axis) < target.get(axis)) {
        return counter + run(kdtree.getRight());
      } else {
        return counter + run(kdtree.getLeft());
      }
    }
  }
}
