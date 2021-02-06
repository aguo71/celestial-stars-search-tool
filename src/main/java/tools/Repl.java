package tools;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing a REPL.
 */
public class Repl {
  private Map<String, Action> commands = new HashMap<>();

  /**
   * Empty constructor.
   */
  public Repl() { }

  /**
   *
   * @return hashmap of commands
   */
  public Map<String, Action> getCommands() {
    return this.commands;
  }

  /**
   * Adds a REPL command and its respective behavioral class to hashmap commands.
   * @param method REPL command to add
   * @param action class representing desired behavior of REPL command
   */
  public void registerAction(String method, Action action) {
    commands.put(method, action);
  }

  /**
   * REPL while loop that continues reads commands from command line and calls appropriate class
   * for expected behavior.
   */
  public void readCommands() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String input;
    // REPL loop
    while (true) {
      try {
        input = reader.readLine();
      } catch (IOException e) {
        System.out.println("ERROR: IOException occurred during REPL reading");
        continue;
      }
      if (input == null) {
        return;
      }
      if (input.equals("")) {
        continue;
      }

      // Parses REPL command, splitting on spaces except within double quotes. Code from:
      // https://stackoverflow.com/questions/366202/regex-for-splitting-a-
      // string-using-space-when-not-surrounded-by-single-or-double
      List<String> matchList = new ArrayList<>();
      Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
      Matcher regexMatcher = regex.matcher(input);
      while (regexMatcher.find()) {
        matchList.add(regexMatcher.group());
      }
      String[] tokens = new String[matchList.size()];
      tokens = matchList.toArray(tokens);
      // Checks if parsed command was registered to hashmap, if so, calls appropriate class.run()
      if (commands.containsKey(tokens[0])) {
        commands.get(tokens[0]).run(tokens);
      } else {
        System.out.println("ERROR: Invalid command");
      }
    }
  }
}
