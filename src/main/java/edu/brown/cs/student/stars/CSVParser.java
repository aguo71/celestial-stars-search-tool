package edu.brown.cs.student.stars;

import java.io.BufferedReader;
import java.io.IOException;

public class CSVParser {

    public CSVParser() {}

    public String[] parseLine(BufferedReader reader, String delims) throws IOException {
        String line = reader.readLine();
        if(line == null) {
            return null;
        } else {
            return line.split(delims);
        }
    }
}
