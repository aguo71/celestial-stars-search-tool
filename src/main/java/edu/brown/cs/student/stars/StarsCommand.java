package edu.brown.cs.student.stars;

import java.io.*;
import java.util.ArrayList;

public class StarsCommand implements Action {
    ArrayList<Star> stars;

    public StarsCommand(ArrayList<Star> stars) {
        this.stars = stars;
    }

    @Override
    public void run(String[] args) {
        if(args.length != 2) {
            System.out.println("ERROR: incorrect number of arguments for stars method");
            return;
        }

        String filename = args[1];
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filename));
        } catch(FileNotFoundException e) {
            System.out.println("ERROR: File does not exist");
            return;
        }

        String firstLine;
        try{
            firstLine = reader.readLine();
        } catch (IOException e) {
            System.out.println("ERROR: IOException occurred during CSV reading");
            return;
        }
        if(!firstLine.equals("StarID,ProperName,X,Y,Z")) {
            System.out.println("ERROR: CSV file format incorrect");
            return;
        }

        StarsParser parser = new StarsParser();
        try {
            ArrayList<Star> tempStars = parser.parse(reader);
            if(stars != null) {
                stars.clear();
            }
            stars.addAll(tempStars);
        } catch (IOException e) {
            System.out.println("ERROR: CSV file format incorrect");
            return;
        }
        System.out.println("Read " + stars.size() + " stars from " + filename);
    }
}
