package edu.brown.cs.student.stars;


import mockaroo.MockCommand;
import tools.KDTree;
import tools.Repl;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to set up a REPL specifically for stars and registers the appropriate repl commands.
 */
public class StarsUniverse {
  private List<Star> stars = new ArrayList<>();
  private KDTree<Star> starTree = new KDTree<>(null, null, null, -1);

  /**
   * Empty constructor.
   */
  public StarsUniverse() { }

  /**
   * Sets up a REPL for the stars assignment and registers repl commands to handle.
   */
  public void run() {
    Repl repl = new Repl();
    StarsCommand starsC = new StarsCommand(stars, starTree);
    NaiveNeighborCommand neighbor = new NaiveNeighborCommand(stars);
    NaiveRadiusCommand radius = new NaiveRadiusCommand(stars);
    MockCommand mockC = new MockCommand();
    NeighborCommand neighborR = new NeighborCommand(starTree, stars);
    RadiusCommand radiusR = new RadiusCommand(starTree, stars);
    repl.registerAction("stars", starsC);
    repl.registerAction("naive_neighbors", neighbor);
    repl.registerAction("naive_radius", radius);
    repl.registerAction("mock", mockC);
    repl.registerAction("neighbors", neighborR);
    repl.registerAction("radius", radiusR);
    repl.readCommands();
  }

}
