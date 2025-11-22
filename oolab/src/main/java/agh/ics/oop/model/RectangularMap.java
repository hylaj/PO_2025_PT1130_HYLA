package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;

public class RectangularMap extends AbstractWorldMap{

    private final Vector2d lowerLeftMapCorner;
    private final Vector2d upperRightMapCorner;
    private final Boundary boundary;


    public RectangularMap(int height, int width) {
        lowerLeftMapCorner = new Vector2d(0,0);
        upperRightMapCorner = new Vector2d(width, height);
        boundary = new Boundary(lowerLeftMapCorner, upperRightMapCorner);

    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return (super.canMoveTo(position) &&
                position.follows(lowerLeftMapCorner) &&
                position.precedes(upperRightMapCorner));

    }

    @Override
    public Boundary  getCurrentBounds() {
        return boundary;
    }
}
