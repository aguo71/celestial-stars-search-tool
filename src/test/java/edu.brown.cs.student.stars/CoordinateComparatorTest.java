package edu.brown.cs.student.stars;

import org.junit.Test;
import tools.CoordinateComparator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class to test Star methods.
 */
public class CoordinateComparatorTest {

  /**
   ** Tests CoordinateComparator compare.
   */
  @Test
  public void testInterfaceMethods() {
    List<Double> test1C = new ArrayList<>();
    test1C.add(2.3);
    test1C.add(3.4);
    test1C.add(-0.2);
    Star test1 = new Star(0, "test1", test1C);

    List<Double> test2C = new ArrayList<>();
    test2C.add(4.2);
    test2C.add(-5.1);
    test2C.add(9.9);
    Star test2 = new Star(1, "test2", test2C);

    List<Double> test3C = new ArrayList<>();
    test3C.add(4.2);
    test3C.add(-5.1);
    test3C.add(9.9);
    Star test3 = new Star(2, "test3", test3C);

    CoordinateComparator<Star> comp = new CoordinateComparator<Star>(0);
    CoordinateComparator<Star> comp2 = new CoordinateComparator<Star>(2);
    CoordinateComparator<Star> comp3 = new CoordinateComparator<Star>(1);

    assertEquals(comp.compare(test1, test2), -1);
    assertEquals(comp.compare(test2, test1), 1);

    assertEquals(comp2.compare(test1, test2), -1);
    assertEquals(comp2.compare(test2, test1), 1);

    assertEquals(comp3.compare(test1, test2), 1);
    assertEquals(comp3.compare(test2, test1), -1);

    assertEquals(comp3.compare(test2, test3), 0);
    assertEquals(comp3.compare(test3, test2), 0);
  }
}
