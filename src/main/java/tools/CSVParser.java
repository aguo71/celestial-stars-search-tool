package tools;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class that parses CSV files.
 */
public class CSVParser {
  /**
   * Empty constructor.
   */
  public CSVParser() { }

  /**
   * Parses a single CSV line.
   * @param reader BufferedReader reading from desired csv file
   * @param delims delims to tokenize each line read with
   * @return array of string tokens in the line read
   * @throws IOException if readLine fails
   */
  public String[] parseLine(BufferedReader reader, String delims) throws IOException {
    String line = reader.readLine();
    if (line == null) {
      return null;
    } else {
      // Tokenizes line based on deliminators, keeping all tokens even if empty
      return line.split(delims, -1);
    }
  }
}
