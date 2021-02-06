package edu.brown.cs.student.stars;

import org.junit.Test;
import tools.DistanceCalculator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class to test distance calculator.
 */
public class DistanceCalculatorTest {

  /**
   ** Tests whether getDistance returns correct answer
   */
  @Test
  public void testGetDistance() {
    List<Double> c1 = new ArrayList<>();
    c1.add(7.0);
    c1.add(4.0);
    c1.add(3.0);
    List<Double> c2 = new ArrayList<>();
    c2.add(17.0);
    c2.add(6.0);
    c2.add(3.0);
    List<Double> c3 = new ArrayList<>();
    c3.add(0.0);
    c3.add(0.0);
    c3.add(0.0);
    List<Double> c4 = new ArrayList<>();
    c4.add(-4.0);
    c4.add(4.0);
    c4.add(-2.0);
    List<Double> c5 = new ArrayList<>();
    c5.add(-3.2);
    c5.add(0.2);
    c5.add(0.0);

    DistanceCalculator tester = new DistanceCalculator();

    assertEquals((Double) tester.getDistance(c1, c2), (Double) 10.198039027185569);
    assertEquals((Double) tester.getDistance(c2, c1), (Double) 10.198039027185569);
    assertEquals((Double) tester.getDistance(c3, c1), (Double) 8.602325267042627);
    assertEquals((Double) tester.getDistance(c4, c1), (Double) 12.083045973594572);
    assertEquals((Double) tester.getDistance(c5, c1), (Double) 11.290704141017954);

  }

}
