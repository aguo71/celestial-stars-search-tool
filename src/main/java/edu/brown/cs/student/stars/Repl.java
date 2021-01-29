package edu.brown.cs.student.stars;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
            String delims = "[ ]+";
            String[] tokens = input.split(delims);

            if(commands.containsKey(tokens[0])) {
                commands.get(tokens[0]).run(tokens);
            } else {
                System.out.println("ERROR: Invalid command");
            }
        }
    }
}
