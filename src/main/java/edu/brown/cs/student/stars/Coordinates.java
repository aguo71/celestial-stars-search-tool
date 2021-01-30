package edu.brown.cs.student.stars;

/**
 * Class representing 3d coordinates.
 */
public class Coordinates {
  private double x;
  private double y;
  private double z;

  /**
   *
   * @param x x coordinate
   * @param y y coordinate
   * @param z z coordinate
   */
  public Coordinates(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * @return x coordinate
   */
  public double getX() {
    return this.x;
  }

  /**
   *
   * @return y coordinate
   */
  public double getY() {
    return this.y;
  }

  /**
   *
   * @return z coordinate
   */
  public double getZ() {
    return this.z;
  }
}
