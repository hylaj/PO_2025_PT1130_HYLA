package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap{

    private final Vector2d lowerLeftMapCorner;
    private final Vector2d upperRightMapCorner;


    public RectangularMap(int height, int width) {
        lowerLeftMapCorner = new Vector2d(0,0);
        upperRightMapCorner = new Vector2d(width, height);

    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return (super.canMoveTo(position) &&
                position.follows(lowerLeftMapCorner) &&
                position.precedes(upperRightMapCorner));

    }

    @Override
    public String toString(){
        return mapVisualizer.draw(lowerLeftMapCorner, upperRightMapCorner);

    }
}
