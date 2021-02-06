package tools;

import java.util.List;

/**
 * Interface representing any object with coordinates.
 */
public interface HasCoordinates {
  /**
   *
   * @return dimension of star
   */
  int numDimensions();
  /**
   * @param index axis of coordinate to get
   * @return index-coordinate of star
   */
  double getCoordinate(int index);

  /**
   * Getter for coordinates.
   * @return coordinates of object
   */
  List<Double> getCoordinates();
}
