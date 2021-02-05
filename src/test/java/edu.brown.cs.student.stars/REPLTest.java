package edu.brown.cs.student.stars;

import mockaroo.MockCommand;
import org.junit.Test;
import tools.Repl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Class to test REPL registerAction method.
 */
public class REPLTest {

  /**
   ** Tests whether registerAction updates hashmap appropriately.
   */
  @Test
  public void testRegister() {
    Repl repl = new Repl();
    List<Star> stars = new ArrayList<>();
    KDTree<Star> starTree = new KDTree<>(null, null, null, 0);
    StarsCommand starsC = new StarsCommand(stars, starTree);
    NaiveNeighborCommand neighbor = new NaiveNeighborCommand(stars);
    NaiveRadiusCommand radius = new NaiveRadiusCommand(stars);
    MockCommand mockC = new MockCommand();
    repl.registerAction("stars", starsC);
    repl.registerAction("naive_neighbors", neighbor);
    repl.registerAction("naive_radius", radius);
    repl.registerAction("mock", mockC);
    Map<String, Action> map = repl.getCommands();
    assertTrue(map.containsKey("stars"));
    assertTrue(map.containsKey("naive_neighbors"));
    assertFalse(map.containsKey("neighbors"));
    assertTrue(map.containsKey("naive_radius"));
    assertTrue(map.containsValue(mockC));
  }
}
