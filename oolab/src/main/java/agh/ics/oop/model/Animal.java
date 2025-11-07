package agh.ics.oop.model;

public class Animal {
    public static final Vector2d LOWER_LEFT = new Vector2d(0, 0);
    public static final Vector2d UPPER_RIGHT = new Vector2d(4, 4);

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
        return "Animal{" +
                "Direction: " + currentDirection +
                ", Position: " + currentPosition +
                '}';
    }

    public boolean isAt(Vector2d position) {
        return currentPosition.equals(position);
    }

    public void move(MoveDirection direction) {

        Vector2d newPosition = currentPosition;

        switch (direction) {
            case RIGHT -> currentDirection = currentDirection.next();
            case LEFT -> currentDirection = currentDirection.previous();
            case FORWARD -> newPosition = currentPosition.add(currentDirection.toUnitVector());
            case BACKWARD -> newPosition = currentPosition.subtract(currentDirection.toUnitVector());
        }

        if (newPosition.precedes(UPPER_RIGHT) && newPosition.follows(LOWER_LEFT)) {
            currentPosition = newPosition;
        }
    }

    public MapDirection getCurrentDirection() {
        return currentDirection;
    }

    public Vector2d getCurrentPosition() {
        return currentPosition;
    }
}
