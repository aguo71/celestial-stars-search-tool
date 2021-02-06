package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class GetKNeighbors<T extends HasCoordinates> {
  private List<Double> target;
  // stores distance from target coordinate -> list of stars at that distance away
  private TreeMap<Double, List<T>> neighbors;
  private int k;
  DistanceCalculator calc = new DistanceCalculator();

  public GetKNeighbors(TreeMap<Double, List<T>> neighbors, List<Double> target, int k){
    this.neighbors = neighbors;
    this.target = target;
    this.k = k;
  }

  public int run(KDTree<T> kdtree) {
    if(kdtree == null) {
      return 0;
    }
    int counter = 0;
    double dist = calc.getDistance(kdtree.getVal().getCoordinates(), target);
    if(neighbors.isEmpty()) {
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
    double axisDist = Math.sqrt(Math.pow(kdtree.getVal().getCoordinate(axis) - target.get(axis), 2));
    double furthestD = neighbors.lastKey();
    if(axisDist < furthestD) {
      return counter + run(kdtree.getLeft()) + run(kdtree.getRight());
    } else {
      if(kdtree.getVal().getCoordinate(axis) < target.get(axis)) {
        return counter + run(kdtree.getRight());
      } else {
        return counter + run(kdtree.getLeft());
      }
    }
  }
}
