package edu.brown.cs.student.stars;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class StarsParser {
    public StarsParser() {}

    public ArrayList<Star> parse(BufferedReader reader) throws IOException, NumberFormatException{
        ArrayList<Star> stars = new ArrayList<>();
        CSVParser parser = new CSVParser();
        String[] tokens;
        tokens = parser.parseLine(reader, ",");
        int id;
        String name;
        double x;
        double y;
        double z;

        while(tokens != null) {
            if(tokens.length != 5) {
                throw new IOException();
            }
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
