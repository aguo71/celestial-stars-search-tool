package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class capable of constructing a KDTree.
 * @param <T> object type implementing HasCoordinates
 */
public class KDTreeConstructor<T extends HasCoordinates> {
  /**
   * Empty constructor.
   */
  public KDTreeConstructor() { }

  /**
   * Builds a KDtree using input list and depth counter.
   * @param subset list of T objects to build a tree from
   * @param depth counter for current depth
   * @return new KDTree
   */
  public KDTree<T> buildTree(List<T> subset, int depth) {
    if (subset.isEmpty()) {
      return null;
    }

    int plane = depth % subset.get(0).numDimensions();

    List<T> subsetCopy = new ArrayList<>(subset);
    Collections.sort(subsetCopy, new CoordinateComparator<T>(plane));
    int middle = subsetCopy.size() / 2;

    return new KDTree<T>(
        subsetCopy.get(middle), buildTree(subsetCopy.subList(0, middle), depth + 1),
        buildTree(subsetCopy.subList(middle + 1, subset.size()), depth + 1), depth);
  }
}
