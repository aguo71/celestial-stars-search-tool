package edu.brown.cs.student.stars;

import tools.CSVParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to parse stars csv data.
 */
public class StarsParser {
  /**
   * Empty constructor.
   */
  public StarsParser() { }

  /**
   * Parses data using an input reader which reads from a csv file and adds new star objects into
   * a list.
   * @param reader BufferedReader reading from csv file in question
   * @return list of stars parsed from csv
   * @throws IOException if parseLine fails
   * @throws NumberFormatException if parsed input cannot be converted to double
   */
  public ArrayList<Star> parse(BufferedReader reader) throws IOException, NumberFormatException {
    ArrayList<Star> stars = new ArrayList<>();
    CSVParser parser = new CSVParser();
    String[] tokens;
    tokens = parser.parseLine(reader, ",");
    int id;
    String name;
    double x;
    double y;
    double z;
    // Parses continuously until EOF
    while (tokens != null) {
      if (tokens.length != 5) {
        throw new IOException();
      }
      // Makes new star for each line read and adds to list
      id = (int) Double.parseDouble(tokens[0]);
      name = tokens[1];
      x = Double.parseDouble(tokens[2]);
      y = Double.parseDouble(tokens[3]);
      z = Double.parseDouble(tokens[4]);
      Star newStar = new Star(id, name, new Coordinates(x, y, z));
      stars.add(newStar);
      tokens = parser.parseLine(reader, ",");
    }
    return stars;
  }

}
