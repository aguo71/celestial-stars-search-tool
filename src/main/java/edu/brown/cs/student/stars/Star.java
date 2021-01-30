package edu.brown.cs.student.stars;

/**
 * Class representing a star.
 */
public class Star {
  private int starID;
  private String name;
  private Coordinates position;

  /**
   *
   * @param starID ID of star
   * @param name name of star
   * @param position position of star
   */
  public Star(int starID, String name, Coordinates position) {
    this.starID = starID;
    this.name = name;
    this.position = position;
  }

  /**
   *
   * @return star ID
   */
  public int getID() {
    return this.starID;
  }

  /**
   *
   * @return name of star
   */
  public String getName() {
    return this.name;
  }

  /**
   *
   * @return coordinates of star
   */
  public Coordinates getCoordinates() {
    return this.position;
  }

}
