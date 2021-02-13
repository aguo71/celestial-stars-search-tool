package edu.brown.cs.student.stars;

import org.junit.Test;
import tools.KDTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class to test starsParser parse method.
 */
public class StarsCommandTest {

  /**
   ** Tests whether parse is populating stars list correctly.
   */
  @Test
  public void testRun() {
    List<Star> stars = new ArrayList<>();
    KDTree<Star> starTree = new KDTree<>(null, null, null, -1);
    Star temp = new Star(-1, null, new ArrayList<>());
    stars.add(temp);

    StarsCommand command = new StarsCommand(stars, starTree);
    String[] args = new String[2];
    args[0] = "stars";
    args[1] = "data/stars/three-star.csv";
    command.run(args);

    assertEquals(stars.size(), 3);
    assertEquals(stars.get(0).getID(), 1);
    assertEquals(stars.get(1).getID(), 2);
    assertEquals(stars.get(2).getID(), 3);

    assertEquals(starTree.getVal().getID(), 2);
    assertEquals(starTree.getLeft().getVal().getID(), 1);
    assertEquals(starTree.getRight().getVal().getID(), 3);

  }
}
