package edu.brown.cs.student.stars;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Repl {
    Map<String, Action> commands = new HashMap<>();

    public Repl() {}

    public void registerAction(String method, Action action) {
        commands.put(method, action);
    }

    public void readCommands() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(true) {
            try {
                input = reader.readLine();
            } catch (IOException e) {
                System.out.println("ERROR: IOException occurred during REPL reading");
                continue;
            }
            if(input == null) {
                return;
            }

            // parses REPL command, splitting on spaces except within double quotes
            // https://stackoverflow.com/questions/366202/regex-for-splitting-a-string-using-space-when-not-surrounded-by-single-or-double

            List<String> matchList = new ArrayList<>();
            Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
            Matcher regexMatcher = regex.matcher(input);
            while (regexMatcher.find()) {
                matchList.add(regexMatcher.group());
            }
            String[] tokens = new String[matchList.size()];
            tokens = matchList.toArray(tokens);

            if(commands.containsKey(tokens[0])) {
                commands.get(tokens[0]).run(tokens);
            } else {
                System.out.println("ERROR: Invalid command");
            }
        }
    }
}
