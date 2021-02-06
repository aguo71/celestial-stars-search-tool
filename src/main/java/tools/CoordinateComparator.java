package tools;

import java.util.Comparator;

/**
 * Comparator for objects with coordinates along a plane.
 * @param <T> type that implements HasCoordinates (e.g. a star)
 */
public class CoordinateComparator<T extends HasCoordinates> implements Comparator<T> {
  private int plane;

  /**
   * Constructor.
   * @param plane axis to compare along
   */
  public CoordinateComparator(int plane) {
    this.plane = plane;
  }

  @Override
  public int compare(T o1, T o2) {
    if (o1.getCoordinate(plane) < o2.getCoordinate(plane)) {
      return -1;
    } else if (o1.getCoordinate(plane) == o2.getCoordinate(plane)) {
      return 0;
    } else {
      return 1;
    }
  }
}
