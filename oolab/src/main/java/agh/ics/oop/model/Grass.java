package agh.ics.oop.model;

public class Grass implements WorldElement{

    private final Vector2d currentPosition ;

    public Grass(Vector2d position) {
        this.currentPosition = position;
    }

    public Vector2d getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public String toString() {
        return "*";
    }
}
