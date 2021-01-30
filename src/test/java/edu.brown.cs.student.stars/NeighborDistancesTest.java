package edu.brown.cs.student.stars;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class to test NeighborDistances method.
 */
public class NeighborDistancesTest {

    private ArrayList<Star> stars;

    /**
     * Sets up arraylist of stars.
     */
    @Before
    public void setUp() {
      stars = new ArrayList<Star>();
      stars.add(new Star(1, "star1", new Coordinates(0, 0, 0)));
      stars.add(new Star(4, "star4", new Coordinates(0, 0, 0)));
      stars.add(new Star(2, "star2", new Coordinates(1, 4, 2)));
      stars.add(new Star(3, "star3", new Coordinates(-1, 5, -2)));
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
      HashMap<Double, ArrayList<Star>> result = tester.findDistances(
          new Coordinates(0, 0, 0));
      assertTrue(result.containsKey(4.58257569495584));
      assertTrue(result.containsKey(5.477225575051661));
      assertTrue(result.containsKey(0.0));
      assertEquals(result.get(0.0).size(), 2);
      tearDown();

    }

  }

