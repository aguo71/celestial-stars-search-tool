package edu.brown.cs.student.stars;


import mockaroo.MockCommand;
import tools.Repl;
import java.util.ArrayList;

/**
 * Class to set up a REPL specifically for stars and registers the appropriate repl commands.
 */
public class StarsUniverse {
  private ArrayList<Star> stars = new ArrayList<>();

  /**
   * Empty constructor.
   */
  public StarsUniverse() { }

  /**
   * Sets up a REPL for the stars assignment and registers repl commands to handle.
   */
  public void run() {
    Repl repl = new Repl();
    StarsCommand starsC = new StarsCommand(stars);
    NaiveNeighborCommand neighbor = new NaiveNeighborCommand(stars);
    NaiveRadiusCommand radius = new NaiveRadiusCommand(stars);
    MockCommand mockC = new MockCommand();
    repl.registerAction("stars", starsC);
    repl.registerAction("naive_neighbors", neighbor);
    repl.registerAction("naive_radius", radius);
    repl.registerAction("mock", mockC);
    repl.readCommands();
  }

}
