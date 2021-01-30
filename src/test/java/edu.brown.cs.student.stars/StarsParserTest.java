package edu.brown.cs.student.stars;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Class to test starsParser parse method.
 */
public class StarsParserTest {

    /**
     ** Tests whether parse is populating stars list correctly.
     */
    @Test
    public void testParse() {
      BufferedReader reader;
      try {
        reader = new BufferedReader(new FileReader("data/stars/three-star.csv"));
      } catch (FileNotFoundException e) {
        return;
      }
      try {
        reader.readLine();
      } catch (IOException e) {
        return;
      }
      StarsParser parser = new StarsParser();
      ArrayList<Star> stars;
      try {
        stars = parser.parse(reader);
      } catch (IOException | NumberFormatException e) {
        return;
      }
      assertEquals(stars.size(), 3);
    }
//
//  /**
//   ** Tests whether parse throws proper exception.
//   */
//  @Test
//  public void testParseException() throws IOException {
//    BufferedReader reader;
//    try {
//      reader = new BufferedReader(new FileReader("data/stars/TooManyStarAttributes.csv"));
//    } catch (FileNotFoundException e) {
//      return;
//    }
//
//    try {
//      reader.readLine();
//    } catch (IOException e) {
//      return;
//    }
//
//    StarsParser parser = new StarsParser();
//    ArrayList<Star> stars;
//    stars = parser.parse(reader);
//  }
}
