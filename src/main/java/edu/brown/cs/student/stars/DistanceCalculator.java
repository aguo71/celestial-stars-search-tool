package edu.brown.cs.student.stars;

public class DistanceCalculator {
    public DistanceCalculator(){}

    public Double getDistance(Coordinates c1, Coordinates c2) {
        return Math.sqrt(Math.pow(c1.x - c2.x, 2)+ Math.pow(c1.y - c2.y, 2) + Math.pow(c1.z - c2.z, 2));
    }
}
