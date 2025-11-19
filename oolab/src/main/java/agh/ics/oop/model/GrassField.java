package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.sqrt;

public class GrassField implements WorldMap {

    private final Map<Vector2d, Grass> grass = new HashMap<>();

    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public GrassField(int grassQuantity) {

        Random random = new Random();

        int grassBound = (int) sqrt(grassQuantity*10);

        while (grass.size() < grassQuantity) {
            int x = random.nextInt(grassBound+1);
            int y = random.nextInt(grassBound+1);

            Vector2d position = new Vector2d(x, y);

            if (!grass.containsKey(position)) {
                grass.put(position, new Grass(position));
            }
        }
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getCurrentPosition();
        if(canMoveTo(position)){
            animals.put(position, animal);
            return true;
        }
        return false;
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
    public WorldElement objectAt(Vector2d position) {
        Animal animal = animals.get(position);
        if(animal != null)
            return animal;
        else
            return grass.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return !animals.containsKey(position);

    }

    @Override
    public String toString(){

       Vector2d upperRightMapCorner = grass.keySet().iterator().next();
        Vector2d lowerLeftMapCorner= upperRightMapCorner;

        for (Vector2d position : grass.keySet()) {
            lowerLeftMapCorner = position.lowerLeft(lowerLeftMapCorner);
            upperRightMapCorner = position.upperRight(upperRightMapCorner);
        }

        for (Vector2d position : animals.keySet()) {
            lowerLeftMapCorner = position.lowerLeft(lowerLeftMapCorner);
            upperRightMapCorner = position.upperRight(upperRightMapCorner);
        }


        return mapVisualizer.draw(lowerLeftMapCorner, upperRightMapCorner);


    }

}
