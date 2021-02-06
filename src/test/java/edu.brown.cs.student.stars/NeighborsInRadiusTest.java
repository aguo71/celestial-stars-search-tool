package edu.brown.cs.student.stars;

import org.junit.Test;
import tools.KDTree;
import tools.KDTreeConstructor;
import tools.NeighborsInRadius;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class to test NeighborsInRadius.
 */
public class NeighborsInRadiusTest {

  /**
   ** Tests whether run algorithm works appropriately.
   */
  @Test
  public void testRun() {
    TreeMap<Double, List<Star>> neighbors = new TreeMap<>();
    List<Double> target = new ArrayList<>();
    target.add(0.0);
    target.add(0.0);
    target.add(0.0);

    NeighborsInRadius<Star> algorithm = new NeighborsInRadius<>(neighbors, target, 1);

    List<Double> test1C = new ArrayList<>();
    test1C.add(0.0);
    test1C.add(0.0);
    test1C.add(0.0);
    Star test1 = new Star(0, "test1", test1C);

    List<Double> test2C = new ArrayList<>();
    test2C.add(1.0);
    test2C.add(0.0);
    test2C.add(0.0);
    Star test2 = new Star(1, "test2", test2C);

    List<Double> test3C = new ArrayList<>();
    test3C.add(-2.0);
    test3C.add(0.0);
    test3C.add(0.0);
    Star test3 = new Star(2, "test3", test3C);

    List<Double> test4C = new ArrayList<>();
    test4C.add(0.0);
    test4C.add(2.0);
    test4C.add(3.0);
    Star test4 = new Star(3, "test4", test4C);

    List<Double> test5C = new ArrayList<>();
    test5C.add(-5.0);
    test5C.add(0.0);
    test5C.add(0.0);
    Star test5 = new Star(4, "test5", test5C);

    KDTreeConstructor<Star> builder = new KDTreeConstructor<>();

    List<Star> stars = new ArrayList<>();
    stars.add(test1);
    KDTree<Star> tree1 = builder.buildTree(stars, 1);

    algorithm.run(tree1);
    assertTrue(neighbors.containsKey(0.0));
    neighbors = new TreeMap<>();

    
  }
}
