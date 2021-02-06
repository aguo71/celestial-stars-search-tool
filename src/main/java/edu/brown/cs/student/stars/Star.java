package edu.brown.cs.student.stars;

import tools.HasCoordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a star.
 */
public class Star implements HasCoordinates {
  private int starID;
  private String name;
  private List<Double> coordinates;

  /**
   *
   * @param starID ID of star
   * @param name name of star
   * @param coordinates position of star
   */
  public Star(int starID, String name, List<Double> coordinates) {
    this.starID = starID;
    this.name = name;
    this.coordinates = new ArrayList<>(coordinates);
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

  public List<Double> getCoordinates() {
    return this.coordinates;
  }

  @Override
  public int numDimensions() {
    return coordinates.size();
  }

  @Override
  public double getCoordinate(int index) {
    return this.coordinates.get(index);
  }

}
