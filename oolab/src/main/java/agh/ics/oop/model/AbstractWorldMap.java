package agh.ics.oop.model;

import agh.ics.oop.model.exception.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getCurrentPosition();
        if(canMoveTo(position)){
            animals.put(position, animal);
        }
        else throw new IncorrectPositionException(animal.getCurrentPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {

        WorldElement mapAnimal = objectAt(animal.getCurrentPosition());
        if (mapAnimal == null || !mapAnimal.equals(animal)) {
            return;
        }

        animals.remove(animal.getCurrentPosition());
        animal.move(direction,this);
        animals.put(animal.getCurrentPosition(), animal);
    }


    @Override
    public boolean isOccupied(Vector2d position){
        return objectAt(position) != null;
    }

    @Override
    public WorldElement objectAt(Vector2d position){
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return !animals.containsKey(position);

    }

    @Override
    public Collection<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

}
