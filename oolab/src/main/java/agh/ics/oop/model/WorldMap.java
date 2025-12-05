package agh.ics.oop.model;

import agh.ics.oop.model.exception.IncorrectPositionException;
import agh.ics.oop.model.util.Boundary;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap extends MoveValidator {

    /**
     * Place a new animal on the map.
     *
     * @param animal The animal to be placed on the map.
     * @return True if the animal was placed. The rules for valid placement are the same as for movement.
     */
    void place(Animal animal) throws IncorrectPositionException;

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(Animal animal, MoveDirection direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMoveTo since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    /**
     * Return an animal at a given position.
     *
     * @param position The position of the animal.
     * @return animal or null if the position is not occupied.
     */
    WorldElement objectAt(Vector2d position);

    /**
     * Returns a collection of all WorldElements (Animals, Grass) currently on the map.
     *
     * @return Collection of WorldElements.
     */
    Collection<WorldElement> getElements();

    Boundary getCurrentBounds();

    UUID getId();


}
