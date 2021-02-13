package edu.brown.cs.student.stars;

import org.junit.Test;
import tools.KDTree;
import tools.KDTreeConstructor;
import tools.NeighborsInRadius;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.Assert.*;

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
    test4C.add(4.0);
    test4C.add(3.0);
    Star test4 = new Star(3, "test4", test4C);

    List<Double> test5C = new ArrayList<>();
    test5C.add(-2.0);
    test5C.add(0.0);
    test5C.add(0.0);
    Star test5 = new Star(4, "test5", test5C);

    List<Double> test6C = new ArrayList<>();
    test6C.add(-5.0);
    test6C.add(0.0);
    test6C.add(0.0);
    Star test6 = new Star(5, "test6", test6C);

    KDTreeConstructor<Star> builder = new KDTreeConstructor<>();

    List<Star> stars0 = new ArrayList<>();
    KDTree<Star> tree0 = builder.buildTree(stars0, 0);
    algorithm.run(tree0);
    assertTrue(neighbors.isEmpty());

    List<Star> stars = new ArrayList<>();
    stars.add(test1);
    KDTree<Star> tree1 = builder.buildTree(stars, 0);
    algorithm.run(tree1);
    assertTrue(neighbors.containsKey(0.0));

    neighbors.clear();
    List<Star> stars2 = new ArrayList<>();
    stars2.add(test1);
    stars2.add(test2);
    KDTree<Star> tree2 = builder.buildTree(stars2, 0);
    algorithm.run(tree2);
    assertTrue(neighbors.containsKey(0.0));
    assertTrue(neighbors.containsKey(1.0));
    assertFalse(neighbors.containsKey(2.0));

    neighbors.clear();
    List<Star> stars3 = new ArrayList<>();
    stars3.add(test1);
    stars3.add(test2);
    stars3.add(test3);
    KDTree<Star> tree3 = builder.buildTree(stars3, 0);
    algorithm.run(tree3);
    assertTrue(neighbors.containsKey(0.0));
    assertTrue(neighbors.containsKey(1.0));
    assertFalse(neighbors.containsKey(2.0));

    neighbors.clear();
    List<Star> stars4 = new ArrayList<>();
    stars4.add(test1);
    stars4.add(test2);
    stars4.add(test3);
    stars4.add(test4);
    KDTree<Star> tree4 = builder.buildTree(stars4, 0);
    algorithm.run(tree4);
    assertTrue(neighbors.containsKey(0.0));
    assertTrue(neighbors.containsKey(1.0));
    assertFalse(neighbors.containsKey(2.0));
    assertFalse(neighbors.containsKey(5.0));

    neighbors.clear();
    List<Star> stars5 = new ArrayList<>();
    stars5.add(test1);
    stars5.add(test2);
    stars5.add(test3);
    stars5.add(test4);
    stars5.add(test5);
    KDTree<Star> tree5 = builder.buildTree(stars5, 0);
    algorithm.run(tree5);
    assertTrue(neighbors.containsKey(0.0));
    assertTrue(neighbors.containsKey(1.0));
    assertFalse(neighbors.containsKey(2.0));
    assertFalse(neighbors.containsKey(5.0));

    algorithm = new NeighborsInRadius<>(neighbors, target, 5);
    neighbors.clear();
    List<Star> stars6 = new ArrayList<>();
    stars6.add(test1);
    stars6.add(test2);
    stars6.add(test3);
    stars6.add(test4);
    stars6.add(test5);
    KDTree<Star> tree6 = builder.buildTree(stars6, 0);
    algorithm.run(tree6);
    assertTrue(neighbors.containsKey(0.0));
    assertTrue(neighbors.containsKey(1.0));
    assertTrue(neighbors.containsKey(2.0));
    assertTrue(neighbors.containsKey(5.0));

    algorithm = new NeighborsInRadius<>(neighbors, target, 0);
    neighbors.clear();
    List<Star> stars7 = new ArrayList<>();
    stars7.add(test1);
    stars7.add(test2);
    stars7.add(test3);
    stars7.add(test4);
    stars7.add(test5);
    KDTree<Star> tree7 = builder.buildTree(stars7, 0);
    algorithm.run(tree7);
    assertTrue(neighbors.containsKey(0.0));
    assertFalse(neighbors.containsKey(1.0));
    assertFalse(neighbors.containsKey(2.0));
    assertFalse(neighbors.containsKey(5.0));

    algorithm = new NeighborsInRadius<>(neighbors, target, 5);
    neighbors.clear();
    List<Star> stars8 = new ArrayList<>();
    stars8.add(test1);
    stars8.add(test2);
    stars8.add(test3);
    stars8.add(test4);
    stars8.add(test5);
    stars8.add(test6);
    KDTree<Star> tree8 = builder.buildTree(stars8, 0);
    algorithm.run(tree8);
    assertTrue(neighbors.containsKey(0.0));
    assertTrue(neighbors.containsKey(1.0));
    assertTrue(neighbors.containsKey(2.0));
    assertTrue(neighbors.containsKey(5.0));
    assertEquals(2, neighbors.get(5.0).size());
    assertEquals(2, neighbors.get(2.0).size());
  }
}
