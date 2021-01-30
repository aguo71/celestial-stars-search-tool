package edu.brown.cs.student.stars;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DistanceCalculatorTest {

  /**
   ** Tests whether getDistance returns correct answer
   */
  @Test
  public void testGetDistance() {
    Coordinates c1 = new Coordinates(7, 4, 3);
    Coordinates c2 = new Coordinates(17, 6, 3);
    Coordinates c3 = new Coordinates(0,0,0);
    Coordinates c4 = new Coordinates(-4, 4, -2);
    Coordinates c5 = new Coordinates(-3.2, .2, 0);

    DistanceCalculator tester = new DistanceCalculator();

    assertEquals((Double) tester.getDistance(c1, c2), (Double) 10.198039027185569);
    assertEquals((Double) tester.getDistance(c2, c1), (Double) 10.198039027185569);
    assertEquals((Double) tester.getDistance(c3, c1), (Double) 8.602325267042627);
    assertEquals((Double) tester.getDistance(c4, c1), (Double) 12.083045973594572);
    assertEquals((Double) tester.getDistance(c5, c1), (Double) 11.290704141017954);

  }

}
