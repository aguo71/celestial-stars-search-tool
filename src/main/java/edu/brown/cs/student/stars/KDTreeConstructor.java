package edu.brown.cs.student.stars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KDTreeConstructor<T extends HasCoordinates> {

  public KDTreeConstructor(){}

  public KDTree<T> buildTree(List<T> subset, int depth) {
    if(subset.isEmpty()) {
      return null;
    }

    int plane = depth % subset.get(0).numDimensions();

    List<T> subsetCopy = new ArrayList<>(subset);
    Collections.sort(subsetCopy, new CoordinateComparator<T>(plane));
    int middle = subsetCopy.size()/2;

    return new KDTree<T>(subset.get(middle), buildTree(subset.subList(0, middle), depth + 1),
        buildTree(subset.subList(middle + 1, subset.size()), depth + 1), depth);
  }
}
