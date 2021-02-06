package tools;

import java.util.Comparator;

public class CoordinateComparator<T extends HasCoordinates> implements Comparator<T> {
  private int plane;

  public CoordinateComparator(int plane) {
    this.plane = plane;
  }

  @Override
  public int compare(T o1, T o2) {
    if (o1.getCoordinate(plane) < o2.getCoordinate(plane)) {
      return -1;
    } else if(o1.getCoordinate(plane) == o2.getCoordinate(plane)) {
      return 0;
    } else {
      return 1;
    }
  }
}