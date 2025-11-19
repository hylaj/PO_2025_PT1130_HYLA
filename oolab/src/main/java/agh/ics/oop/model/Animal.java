package agh.ics.oop.model;

import java.util.Objects;

public class Animal implements WorldElement{

    private  MapDirection currentDirection = MapDirection.NORTH;
    private  Vector2d currentPosition;


    public Animal() {
        this(new Vector2d(2,2));
    }

    public Animal(Vector2d position) {
        currentPosition = position;
    }

    @Override
    public String toString() {
        return switch(currentDirection) {
            case NORTH -> "^";
            case WEST -> "<";
            case EAST -> ">";
            case SOUTH -> "v";
        };
    }

    public boolean isAt(Vector2d position) {
        return currentPosition.equals(position);
    }

    public void move(MoveDirection direction, MoveValidator moveValidator) {

        Vector2d newPosition = currentPosition;

        switch (direction) {
            case RIGHT -> currentDirection = currentDirection.next();
            case LEFT -> currentDirection = currentDirection.previous();
            case FORWARD -> newPosition = currentPosition.add(currentDirection.toUnitVector());
            case BACKWARD -> newPosition = currentPosition.subtract(currentDirection.toUnitVector());
        }

        if (moveValidator.canMoveTo(newPosition)) {
            currentPosition = newPosition;
        }
    }

    public MapDirection getCurrentDirection() {
        return currentDirection;
    }

    public Vector2d getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Animal))
            return false;
        Animal that = (Animal) other;
        return this.currentDirection == that.currentDirection
                && this.currentPosition.equals(that.currentPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentDirection, currentPosition);
    }

}
