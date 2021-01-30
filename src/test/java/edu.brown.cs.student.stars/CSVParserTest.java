package edu.brown.cs.student.stars;

import org.junit.Test;
import tools.CSVParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import static org.junit.Assert.assertEquals;

/**
 * Class to test CSVParser parsing.
 */
public class CSVParserTest {

  /**
   ** Tests whether the CSVParser is tokenizing correctly.
   */
  @Test
  public void testParseLine() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader("data/stars/one-star.csv"));
    } catch (FileNotFoundException e) {
      return;
    }
    try {
      reader.readLine();
    } catch (IOException e) {
      return;
    }
    CSVParser parser = new CSVParser();
    String[] tokens;
    try {
      tokens = parser.parseLine(reader, ",");
    } catch (IOException e) {
      return;
    }
    assertEquals(tokens[0], "1");
    assertEquals(tokens[1], "Lonely Star");
    assertEquals(tokens[2], "5");
    assertEquals(tokens[3], "-2.24");
    assertEquals(tokens[4], "10.04");
  }

}

