package agh.ics.oop.model;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{

    private final Map<Vector2d, Grass> grass = new HashMap<>();

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
    public WorldElement objectAt(Vector2d position) {
        if (super.objectAt(position) != null)
            return super.objectAt(position);

        return grass.get(position);
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
