package edu.brown.cs.student.stars;

import tools.Action;
import tools.KDTree;
import tools.KDTreeConstructor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Class representing the stars repl command and behavior.
 */
public class StarsCommand implements Action {
  private List<Star> stars;
  private KDTree<Star> starTree;
  /**
   *
   * @param stars arraylist of stars
   * @param starTree KDTree of stars
   */
  public StarsCommand(List<Star> stars, KDTree<Star> starTree) {
    this.stars = stars;
    this.starTree = starTree;
  }

  @Override
  public void run(String[] args) {
    if (args.length != 2) {
      System.out.println("ERROR: incorrect number of arguments for stars method");
      return;
    }

    String filename = args[1];
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(filename));
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: File does not exist");
      return;
    }

    String firstLine;
    try {
      firstLine = reader.readLine();
    } catch (IOException e) {
      System.out.println("ERROR: IOException occurred during CSV reading");
      return;
    }
    if (!firstLine.equals("StarID,ProperName,X,Y,Z")) {
      System.out.println("ERROR: CSV file format incorrect");
      return;
    }
    // Parses csv data into an arraylist of stars
    StarsParser parser = new StarsParser();
    try {
      List<Star> tempStars = parser.parse(reader);
      if (stars != null) {
        stars.clear();
      }
      stars.addAll(tempStars);
    } catch (IOException | NumberFormatException e) {
      System.out.println("ERROR: CSV file format incorrect");
      return;
    }
    KDTree<Star> newTree = new KDTreeConstructor<Star>().buildTree(stars, 0);
    if(newTree == null) {
      starTree.copy(new KDTree<Star>(null, null, null, 0));
    } else {
      starTree.copy(newTree);
    }
    System.out.println("Read " + stars.size() + " stars from " + filename);
  }
}
