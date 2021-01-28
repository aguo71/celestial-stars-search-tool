package edu.brown.cs.student.stars;

import java.util.*;

public class NaiveNeighborCommand implements Action {
    ArrayList<Star> stars;
    HashMap<Double, ArrayList<Star>> neighbors;

    public NaiveNeighborCommand(ArrayList<Star> stars) {
        this.stars = stars;
    }

    private ArrayList<Star> getNNeighbors(Integer n) {
        ArrayList<Star> closest = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<Double> (neighbors.keySet());
        Collections.sort(distances);
        ArrayList<Star> nearbyStars;
        int counter = 0;
        for(Double distance: distances) {
            if(counter == n) {
                break;
            }
            nearbyStars = neighbors.get(distance);
            counter += nearbyStars.size();
            if(counter > n) {
                Collections.shuffle(nearbyStars);
                int needed = n - (counter - nearbyStars.size());
                for(int i = 0; i < needed; i ++) {
                    closest.add(nearbyStars.get(i));
                }
            } else {
                Collections.shuffle(nearbyStars);
                closest.addAll(nearbyStars);
            }
        }
        return closest;
    }

    @Override
    public void run(String[] args) {
        if(args.length != 5 && args.length != 3) {
            System.out.println("ERROR: incorrect number of arguments for naive_neighbors method");
            return;
        }

        if(stars.size() == 0) {
            System.out.println("ERROR: No star data exists");
            return;
        }

        if(Integer.parseInt(args[1]) < 0) {
            System.out.println("ERROR: Neighbor count must be non-negative");
            return;
        }

        NeighborDistances calculator = new NeighborDistances(stars);
        if (args.length == 5) {
            neighbors = calculator.findDistances(new Coordinates(Double.parseDouble(args[2]),
                    Double.parseDouble(args[3]), Double.parseDouble(args[4])));
            ArrayList<Star> closest = getNNeighbors(Integer.parseInt(args[1]));
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
            ArrayList<Star> closest = getNNeighbors(Integer.parseInt(args[1])+1);
            closest.remove(toRemove);
            for(Star star: closest) {
                System.out.println(star.getID());
            }
        }
    }
}
