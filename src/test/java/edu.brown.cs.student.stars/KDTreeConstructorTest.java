package edu.brown.cs.student.stars;

import org.junit.Test;
import tools.KDTree;
import tools.KDTreeConstructor;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Class to test KDTreeConstructor build method.
 */
public class KDTreeConstructorTest {

  /**
   ** Tests whether registerAction updates hashmap appropriately.
   */
  @Test
  public void testBuildTree() {
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

    List<Double> test4C = new ArrayList<>();
    test4C.add(-2.0);
    test4C.add(10.0);
    test4C.add(-9.0);
    Star test4 = new Star(3, "test4", test4C);

    List<Double> test5C = new ArrayList<>();
    test5C.add(-5.0);
    test5C.add(-2.0);
    test5C.add(1.0);
    Star test5 = new Star(4, "test5", test5C);

    KDTreeConstructor<Star> builder = new KDTreeConstructor<>();
    List<Star> stars = new ArrayList<>();
    stars.add(test1);
    KDTree<Star> tree1 = builder.buildTree(stars, 1);
    assertEquals(tree1.getVal(), test1);
    assertEquals(tree1.getDepth(), 1);
    assertNull(tree1.getLeft());
    assertNull(tree1.getRight());


    List<Star> stars2 = new ArrayList<>();
    stars2.add(test1);
    stars2.add(test2);
    KDTree<Star> tree2 = builder.buildTree(stars2, 0);
    assertEquals(tree2.getVal(), test2);
    assertEquals(tree2.getDepth(), 0);
    assertEquals(tree2.getLeft().getVal(), test1);
    assertEquals(tree2.getLeft().getDepth(), 1);
    assertNull(tree2.getRight());
    assertNull(tree2.getLeft().getLeft());
    assertNull(tree2.getLeft().getRight());

    List<Star> stars3 = new ArrayList<>();
    stars3.add(test1);
    stars3.add(test2);
    stars3.add(test3);
    KDTree<Star> tree3 = builder.buildTree(stars3, 0);
    assertEquals(tree3.getVal(), test1);
    assertEquals(tree3.getDepth(), 0);
    assertEquals(tree3.getLeft().getVal(), test3);
    assertEquals(tree3.getRight().getVal(), test2);

    List<Star> stars4 = new ArrayList<>();
    stars4.add(test1);
    stars4.add(test2);
    stars4.add(test3);
    stars4.add(test4);
    KDTree<Star> tree4 = builder.buildTree(stars4, 0);
    assertEquals(tree4.getVal(), test1);
    assertEquals(tree4.getLeft().getVal(), test4);
    assertEquals(tree4.getRight().getVal(), test2);
    assertEquals(tree4.getLeft().getLeft().getVal(), test3);

    List<Star> stars5 = new ArrayList<>();
    stars5.add(test1);
    stars5.add(test2);
    stars5.add(test3);
    stars5.add(test5);
    KDTree<Star> tree5 = builder.buildTree(stars5, 0);
    assertEquals(tree5.getVal(), test1);
    assertEquals(tree5.getLeft().getVal(), test3);
    assertEquals(tree5.getRight().getVal(), test2);
    assertEquals(tree5.getLeft().getLeft().getVal(), test5);

    List<Star> stars6 = new ArrayList<>();
    stars6.add(test1);
    stars6.add(test2);
    stars6.add(test3);
    stars6.add(test5);
    stars6.add(test4);
    KDTree<Star> tree6 = builder.buildTree(stars6, 0);
    assertEquals(tree6.getVal(), test3);
    assertEquals(tree6.getLeft().getVal(), test4);
    assertEquals(tree6.getRight().getVal(), test2);
    assertEquals(tree6.getLeft().getLeft().getVal(), test5);
    assertEquals(tree6.getRight().getLeft().getVal(), test1);
  }
}
