package edu.brown.cs.student.stars;

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
}
