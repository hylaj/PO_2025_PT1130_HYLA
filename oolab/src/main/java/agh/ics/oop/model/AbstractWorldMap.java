package agh.ics.oop.model;

import agh.ics.oop.model.exception.IncorrectPositionException;
import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected final List<MapChangeListener> observers = new ArrayList<>();

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }
    public void notifyObservers(String message){
        for (MapChangeListener observer : observers){
            observer.mapChanged(this, message);
        }

    }

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getCurrentPosition();
        if(canMoveTo(position)){
            animals.put(position, animal);
            notifyObservers("Animal was placed at " + animal.getCurrentPosition());
        }
        else throw new IncorrectPositionException(animal.getCurrentPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {

        WorldElement mapAnimal = objectAt(animal.getCurrentPosition());
        if (mapAnimal == null || !mapAnimal.equals(animal)) {
            return;
        }
        Vector2d oldPosition = animal.getCurrentPosition();

        animals.remove(oldPosition);
        animal.move(direction,this);
        animals.put(animal.getCurrentPosition(), animal);
        notifyObservers("Animal was moved from " + oldPosition + " to " + animal.getCurrentPosition());
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

    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary currentBounds = getCurrentBounds();
        return mapVisualizer.draw(currentBounds.lowerLeftMapCorner(), currentBounds.upperRightMapCorner());
    }





}
