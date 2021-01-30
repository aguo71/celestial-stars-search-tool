package edu.brown.cs.student.stars;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to calculate distance between all stars in the dataset stars and a given point.
 */
public class NeighborDistances {
  private ArrayList<Star> stars;

  /**
   *
   * @param stars dataset of stars
   */
  public NeighborDistances(ArrayList<Star> stars) {
    this.stars = stars;
  }

  /**
   * Calculaes distances between stars in the dataset stars and an input coordinate.
   * @param coord coordinate to calculate distance from
   * @return HashMap mapping distance from input coordinate to list of stars at that distance
   */
  public HashMap<Double, ArrayList<Star>> findDistances(Coordinates coord) {
    HashMap<Double, ArrayList<Star>> neighbors = new HashMap();
    for (Star star : stars) {
      // Calculates distance from input coordinate
      DistanceCalculator calc = new DistanceCalculator();
      Double distance = calc.getDistance(coord, star.getCoordinates());
      // Adds (distance, star) pair to hashmap
      if (!neighbors.containsKey(distance)) {
        neighbors.put(distance, new ArrayList<Star>());
      }
      neighbors.get(distance).add(star);
    }
    return neighbors;
  }


}
