package edu.brown.cs.student.stars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to calculate distance between all stars in the dataset stars and a given point.
 */
public class NeighborDistances {
  private List<Star> stars;

  /**
   *
   * @param stars dataset of stars
   */
  public NeighborDistances(List<Star> stars) {
    this.stars = stars;
  }

  /**
   * Calculaes distances between stars in the dataset stars and an input coordinate.
   * @param coords coordinate to calculate distance from
   * @return HashMap mapping distance from input coordinate to list of stars at that distance
   */
  public Map<Double, List<Star>> findDistances(List<Double> coords) {
    HashMap<Double, List<Star>> neighbors = new HashMap<>();
    for (Star star : stars) {
      // Calculates distance from input coordinate
      DistanceCalculator calc = new DistanceCalculator();
      Double distance = calc.getDistance(coords, star.getCoordinates());
      // Adds (distance, star) pair to hashmap
      if (!neighbors.containsKey(distance)) {
        neighbors.put(distance, new ArrayList<Star>());
      }
      neighbors.get(distance).add(star);
    }
    return neighbors;
  }


}
