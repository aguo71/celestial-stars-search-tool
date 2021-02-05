package edu.brown.cs.student.stars;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class to test NeighborDistances method.
 */
public class NeighborDistancesTest {

    private List<Star> stars;

    /**
     * Sets up arraylist of stars.
     */
    @Before
    public void setUp() {
      stars = new ArrayList<Star>();
      List<Double> c1 = new ArrayList<>();
      c1.add(1.0);
      c1.add(4.0);
      c1.add(2.0);
      List<Double> c2 = new ArrayList<>();
      c2.add(0.0);
      c2.add(0.0);
      c2.add(0.0);
      List<Double> c3 = new ArrayList<>();
      c3.add(0.0);
      c3.add(0.0);
      c3.add(0.0);
      List<Double> c4 = new ArrayList<>();
      c4.add(-1.0);
      c4.add(5.0);
      c4.add(-2.0);

      stars.add(new Star(1, "star1", c2));
      stars.add(new Star(4, "star4", c3));
      stars.add(new Star(2, "star2", c1));
      stars.add(new Star(3, "star3", c4));
    }

    /**
     * Resets the arraylist.
     */
    @After
    public void tearDown() {
      stars = null;
    }

    /**
     ** Tests whether the proper hashmap of distances is being created.
     */

    @Test
    public void testFindDistances() {
      setUp();
      NeighborDistances tester = new NeighborDistances(stars);
      List<Double> c1 = new ArrayList<>();
      c1.add(0.0);
      c1.add(0.0);
      c1.add(0.0);
      Map<Double, List<Star>> result = tester.findDistances(c1);
      assertTrue(result.containsKey(4.58257569495584));
      assertTrue(result.containsKey(5.477225575051661));
      assertTrue(result.containsKey(0.0));
      assertEquals(result.get(0.0).size(), 2);
      tearDown();

    }

  }

