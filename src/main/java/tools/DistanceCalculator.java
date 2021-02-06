package tools;

import java.util.List;

/**
 * Class for a method to find distance between two sets of coordinates.
 */
public class DistanceCalculator {
  /**
   * Empty constructor.
   */
  public DistanceCalculator() { }

  /**
   * Finds Euclidean distance of 2 sets of coordinates.
   * @param c1 coordinate 1
   * @param c2 coordinate 2
   * @return distance between input coordinates
   */
  public double getDistance(List<Double> c1, List<Double> c2) {
    if (c1.size() != c2.size()) {
      throw new RuntimeException("ERROR: Can't calculate distance");
    }

    double dist = 0;
    for (int x = 0; x < c1.size(); x++) {
      dist += Math.pow(c1.get(x) - c2.get(x), 2);
    }
    return Math.sqrt(dist);
  }
}
