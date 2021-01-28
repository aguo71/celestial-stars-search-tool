package edu.brown.cs.student.stars;

import java.util.ArrayList;
import java.util.HashMap;

public class NeighborDistances {
    ArrayList<Star> stars;

    public NeighborDistances(ArrayList<Star> stars) {
        this.stars = stars;
    }

    public HashMap<Double, ArrayList<Star>> findDistances(Coordinates coord) {
        HashMap<Double, ArrayList<Star>> neighbors = new HashMap();
        for(Star star: stars) {
            DistanceCalculator calc = new DistanceCalculator();
            Double distance = calc.getDistance(coord, star.getCoordinates());
            if (!neighbors.containsKey(distance)) {
                neighbors.put(distance, new ArrayList<Star>());
            }
            neighbors.get(distance).add(star);
        }
        return neighbors;
    }


}
