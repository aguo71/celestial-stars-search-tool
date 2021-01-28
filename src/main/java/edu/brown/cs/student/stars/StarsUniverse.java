package edu.brown.cs.student.stars;

import java.util.ArrayList;

public class StarsUniverse {
    ArrayList<Star> stars = new ArrayList<>();

    public StarsUniverse() {}

    public void run() {
        Repl repl = new Repl();
        StarsCommand starsC = new StarsCommand(stars);
        NaiveNeighborCommand neighbor = new NaiveNeighborCommand(stars);
        NaiveRadiusCommand radius = new NaiveRadiusCommand(stars);
        repl.registerAction("stars", starsC);
        repl.registerAction("naive_neighbors", neighbor);
        repl.registerAction("naive_radius", radius);
        repl.readCommands();
    }

}
