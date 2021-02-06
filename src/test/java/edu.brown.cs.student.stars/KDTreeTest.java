package edu.brown.cs.student.stars;

import org.junit.Test;
import tools.KDTree;
import tools.KDTreeConstructor;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

/**
 * Class to test KDTree copy method.
 */
public class KDTreeTest {

  /**
   ** Tests whether registerAction updates hashmap appropriately.
   */
  @Test
  public void testCopy() {
    List<Double> test1C = new ArrayList<>();
    test1C.add(0.0);
    test1C.add(0.0);
    test1C.add(0.0);
    Star test1 = new Star(0, "test1", test1C);

    List<Double> test2C = new ArrayList<>();
    test2C.add(1.0);
    test2C.add(3.4);
    test2C.add(4.2);
    Star test2 = new Star(1, "test2", test2C);

    List<Double> test3C = new ArrayList<>();
    test3C.add(-1.0);
    test3C.add(7.3);
    test3C.add(-4.2);
    Star test3 = new Star(2, "test3", test3C);

    List<Star> stars = new ArrayList<>();
    stars.add(test1);
    stars.add(test2);
    stars.add(test3);

    KDTreeConstructor<Star> builder = new KDTreeConstructor<>();
    KDTree<Star> tree = new KDTree<Star>(null, null, null, 0);
    KDTree<Star> toCopy = builder.buildTree(stars, 0);
    tree.copy(toCopy);

    assertEquals(tree.getVal(), test1);
    assertEquals(tree.getLeft().getVal(), test3);
    assertEquals(tree.getRight().getVal(), test2);

  }
}
