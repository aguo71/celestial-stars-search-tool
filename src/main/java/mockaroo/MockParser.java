package mockaroo;

import tools.CSVParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to parse Mock csv files.
 */
public class MockParser {
  /**
   * Empty constructor.
   */
  public MockParser() { }

  /**
   * Parses data using an input reader which reads from a csv file and adds mockperson objects into
   * a list.
   * @param reader BufferedReader reading from csv file in question
   * @return list of mocks parsed from csv
   * @throws IOException if parseLine fails
   */
  public ArrayList<MockPerson> parse(BufferedReader reader) throws IOException {
    ArrayList<MockPerson> mocks = new ArrayList<>();
    CSVParser parser = new CSVParser();
    String[] tokens;
    tokens = parser.parseLine(reader, ",");
    String time;
    String fname;
    String lname;
    String gender;
    String address;
    String email;
    // Parses continuously until EOF
    while (tokens != null) {
      // Makes new mockperson for each line read and adds to list
      if (tokens.length == 5) {
        time = tokens[0];
        fname = tokens[1];
        lname = tokens[2];
        email = tokens[3];
        gender = tokens[4];
        address = "";
      } else if (tokens.length > 6) {
        throw new IOException();
      } else {
        time = tokens[0];
        fname = tokens[1];
        lname = tokens[2];
        email = tokens[3];
        gender = tokens[4];
        address = tokens[5];
      }
      MockPerson newMock = new MockPerson(time, fname, lname, email, gender, address);
      mocks.add(newMock);
      tokens = parser.parseLine(reader, ",");
    }
    return mocks;
  }
}
