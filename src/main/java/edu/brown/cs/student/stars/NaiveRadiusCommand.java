package edu.brown.cs.student.stars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class NaiveRadiusCommand implements Action {
    ArrayList<Star> stars;
    HashMap<Double, ArrayList<Star>> neighbors;

    public NaiveRadiusCommand(ArrayList<Star> stars) {
        this.stars = stars;
    }

    private ArrayList<Star> getNeighborsInRadius(Double radius) {
        ArrayList<Star> inRange = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<Double> (neighbors.keySet());
        Collections.sort(distances);
        ArrayList<Star> nearbyStars;
        for(Double distance: distances) {
            if(distance > radius) {
                continue;
            }
            nearbyStars = neighbors.get(distance);
            Collections.shuffle(nearbyStars);
            inRange.addAll(nearbyStars);
        }
        return inRange;
    }

    @Override
    public void run(String[] args) {
        if(args.length != 5 && args.length != 3) {
            System.out.println("ERROR: incorrect number of arguments for naive_radius method");
            return;
        }

        if(stars.size() == 0) {
            System.out.println("ERROR: No star data exists");
            return;
        }

        if(Double.parseDouble(args[1]) < 0) {
            System.out.println("ERROR: Radius must be non-negative");
            return;
        }

        NeighborDistances calculator = new NeighborDistances(stars);
        if (args.length == 5) {
            neighbors = calculator.findDistances(new Coordinates(Double.parseDouble(args[2]),
                    Double.parseDouble(args[3]), Double.parseDouble(args[4])));
            ArrayList<Star> closest = getNeighborsInRadius(Double.parseDouble(args[1]));
            for(Star star: closest) {
                System.out.println(star.getID());
            }
        } else {
            String toFind = args[2].replaceAll("\"", "");
            Coordinates coord = null;
            Star toRemove = null;
            for(Star star: stars) {
                if(star.getName().equals(toFind)){
                    coord = star.getCoordinates();
                    toRemove = star;
                    break;
                }
            }

            if(coord == null) {
                System.out.println("ERROR: Star not found");
                return;
            }

            neighbors = calculator.findDistances(coord);
            ArrayList<Star> closest = getNeighborsInRadius(Double.parseDouble(args[1]));
            closest.remove(toRemove);
            for(Star star: closest) {
                System.out.println(star.getID());
            }
        }
    }
}
