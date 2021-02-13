package edu.brown.cs.student.stars;


import mockaroo.MockCommand;
import tools.KDTree;
import tools.Repl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Class to set up a REPL specifically for stars and registers the appropriate repl commands.
 */
public class StarsUniverse {
  private List<Star> stars = new ArrayList<>();
  private KDTree<Star> starTree = new KDTree<>(null, null, null, -1);
  NaiveNeighborCommand neighbor;
  NaiveRadiusCommand radius;
  NeighborCommand neighborR;
  RadiusCommand radiusR;
  private List<Star> guiOutput = new ArrayList<>();
  /**
   * Empty constructor.
   */
  public StarsUniverse() { }

  /**
   * Sets up a REPL for the stars assignment and registers repl commands to handle.
   */
  public void run() {
    Star temp = new Star(-1, null, new ArrayList<>());
    stars.add(temp);
    Repl repl = new Repl();
    StarsCommand starsC = new StarsCommand(stars, starTree);
    neighbor = new NaiveNeighborCommand(stars);
    radius = new NaiveRadiusCommand(stars);
    MockCommand mockC = new MockCommand();
    neighborR = new NeighborCommand(starTree, stars);
    radiusR = new RadiusCommand(starTree, stars);
    repl.registerAction("stars", starsC);
    repl.registerAction("naive_neighbors", neighbor);
    repl.registerAction("naive_radius", radius);
    repl.registerAction("mock", mockC);
    repl.registerAction("neighbors", neighborR);
    repl.registerAction("radius", radiusR);
    repl.readCommands();
  }

  /**
   * Method for calling naive_neighbors through GUI input.
   * @param args gui inputs
   * @return string displaying error message
   */
  public String guiQNaiveNeighbors(String[] args) {
    String err = neighbor.run(args);
    this.guiOutput = neighbor.getResults();
    return err;
  }

  /**
   * Method for calling naive_radius through GUI input.
   * @param args gui inputs
   * @return string displaying error message
   */
  public String guiQNaiveRadius(String[] args) {
    String err = radius.run(args);
    this.guiOutput = radius.getResults();
    return err;
  }

  /**
   * Method for calling neighbors through GUI input.
   * @param args gui inputs
   * @return string displaying error message
   */
  public String guiQNeighbors(String[] args) {
    String err = neighborR.run(args);
    TreeMap<Double, List<Star>> neighbors = neighborR.getResults();
    List<Star> toRet = new ArrayList<>();
    // Prints ID of each star in neighbors except input star
    for (Double distance : neighbors.keySet()) {
      Collections.shuffle(neighbors.get(distance));
      for (Star star : neighbors.get(distance)) {
        toRet.add(star);
      }
    }
    this.guiOutput = toRet;
    return err;
  }

  /**
   * Method for calling radius through GUI input.
   * @param args gui inputs
   * @return string displaying error message
   */
  public String guiQRadius(String[] args) {
    String err = radiusR.run(args);
    TreeMap<Double, List<Star>> neighbors = radiusR.getResults();
    List<Star> toRet = new ArrayList<>();
    // Prints ID of each star in neighbors except input star
    for (Double distance : neighbors.keySet()) {
      Collections.shuffle(neighbors.get(distance));
      for (Star star : neighbors.get(distance)) {
        toRet.add(star);
      }
    }
    this.guiOutput = toRet;
    return err;
  }

  /**
   * Getter for guiOutput
   * @return list of stars returned through gui command
   */
  public List<Star> getGuiOutput() {
    return this.guiOutput;
  }

}
