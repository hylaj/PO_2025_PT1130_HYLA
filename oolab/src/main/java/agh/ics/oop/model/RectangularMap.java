package agh.ics.oop.model;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import agh.ics.oop.model.util.MapVisualizer;


public class RectangularMap implements WorldMap {

    private final Map<Vector2d, Animal> animals;
    private final MapVisualizer mapVisualizer;
    private final Vector2d lowerLeftMapCorner;
    private final Vector2d upperRightMapCorner;


    public RectangularMap(int height, int width) {
        lowerLeftMapCorner = new Vector2d(0,0);
        upperRightMapCorner = new Vector2d(width, height);
        animals = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);

    }

    @Override
    public boolean place(Animal animal){
        Vector2d position = animal.getCurrentPosition();
        if(canMoveTo(position)){
            animals.put(position, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction){

        if (objectAt(animal.getCurrentPosition()) != animal){
            return;
        }

        animals.remove(animal.getCurrentPosition());
        animal.move(direction,this);
        animals.put(animal.getCurrentPosition(), animal);
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position){
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return (position.follows(lowerLeftMapCorner) && position.follows(upperRightMapCorner) && !isOccupied(position));

    }

    @Override
    public String toString(){
        return mapVisualizer.draw(lowerLeftMapCorner, upperRightMapCorner);

    }
}
