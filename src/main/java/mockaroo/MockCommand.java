package mockaroo;

import edu.brown.cs.student.stars.Action;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class representing mock repl command.
 */
public class MockCommand implements Action {
  /**
   * Empty constructor.
   */
  public MockCommand() { }

  @Override
  public void run(String[] args) {
    if (args.length != 2) {
      System.out.println("ERROR: incorrect number of arguments for mock method");
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
    if (!firstLine.equals("time,first_name,last_name,email,gender,address")) {
      System.out.println("ERROR: CSV file format incorrect");
      return;
    }

    // Parses csv data into an arraylist of MockPersons
    ArrayList<MockPerson> dataset = new ArrayList<>();
    MockParser parser = new MockParser();
    try {
      dataset = parser.parse(reader);
    } catch (IOException | NumberFormatException e) {
      System.out.println("ERROR: CSV file format incorrect");
      return;
    }
    for (MockPerson p: dataset) {
      System.out.println(p.toString());
    }

  }
}