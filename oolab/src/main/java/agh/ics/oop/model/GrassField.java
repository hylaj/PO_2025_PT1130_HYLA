package agh.ics.oop.model;


import java.util.*;

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

        Collection<WorldElement> elements = getElements();

        if (elements.isEmpty()) {
            return mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(0, 0));
        }

        Vector2d upperRightMapCorner = elements.iterator().next().getCurrentPosition();
        Vector2d lowerLeftMapCorner = upperRightMapCorner;

        for (WorldElement element : elements) {
            Vector2d position = element.getCurrentPosition();

            lowerLeftMapCorner = position.lowerLeft(lowerLeftMapCorner);
            upperRightMapCorner = position.upperRight(upperRightMapCorner);
        }

        return mapVisualizer.draw(lowerLeftMapCorner, upperRightMapCorner);

    }

    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> elements = super.getElements();
        elements.addAll(grass.values());

        return elements;

    }

}
