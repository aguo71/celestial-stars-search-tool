package edu.brown.cs.student.stars;

/**
 * Class for a method to find distance between two 3d coordinates.
 */
public class DistanceCalculator {
  /**
   * Empty constructor.
   */
  public DistanceCalculator() { }

  /**
   * Finds Euclidean distance of 2 3d coordinates.
   * @param c1 coordinate 1
   * @param c2 coordinate 2
   * @return distance between input coordinates
   */
  public Double getDistance(Coordinates c1, Coordinates c2) {
    return Math.sqrt(
        Math.pow(c1.getX() - c2.getX(), 2)
            + Math.pow(c1.getY() - c2.getY(), 2) + Math.pow(c1.getZ() - c2.getZ(), 2));
  }
}
