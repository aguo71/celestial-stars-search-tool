package edu.brown.cs.student.stars;

public class Star {
    private int starID;
    private String name;
    private Coordinates position;

    public Star(int starID, String name, Coordinates position) {
        this.starID = starID;
        this.name = name;
        this.position = position;
    }

    public int getID() {
        return this.starID;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.position;
    }

}
