package tools;

import java.util.List;

public interface HasCoordinates {
  /**
   *
   * @return dimension of star
   */
  int numDimensions();
  /**
   *
   * @return index-coordinate of star
   */
  double getCoordinate(int index);

  List<Double> getCoordinates();
}
