package edu.brown.cs.student.stars;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

/**
 * Class to test Star methods.
 */
public class StarTest {

  /**
   ** Tests whether registerAction updates hashmap appropriately.
   */
  @Test
  public void testInterfaceMethods() {
    List<Double> test1C = new ArrayList<>();
    test1C.add(2.3);
    test1C.add(3.4);
    test1C.add(-0.2);
    Star test1 = new Star(0, "test1", test1C);

    assertEquals(test1.numDimensions(), 3);
    assertEquals(test1.getCoordinate(2), -0.2, 0.00);
    assertEquals(test1.getCoordinate(5), -0.2, 0.00);
  }
}
