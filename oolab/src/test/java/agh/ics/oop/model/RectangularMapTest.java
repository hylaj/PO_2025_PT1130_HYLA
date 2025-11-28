package agh.ics.oop.model;

import agh.ics.oop.model.exception.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    private final RectangularMap map = new RectangularMap(4,4);

    @Test
    void canBePlacedOnEmptyField() {
        //given
        Animal animal = new Animal(new Vector2d(2, 3));

        //when & then
        assertDoesNotThrow(() -> map.place(animal));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));

    }

    @Test
    void cannotBePlacedOnOccupiedField(){

        //given
        Animal animal = new Animal(new Vector2d(2, 3));
        Animal otherAnimal = new Animal(new Vector2d(2,3));

        //when&then
        assertDoesNotThrow(() -> map.place(animal));
        assertThrows(IncorrectPositionException.class,
                () -> map.place(otherAnimal)
        );

    }

    @Test
    void canMoveToInsideMap(){
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
        assertTrue(map.canMoveTo(new Vector2d(2,2)));
    }

    @Test
    void cannotMoveOutOfMap(){

        assertFalse(map.canMoveTo(new Vector2d(-1, 0)));
        assertFalse(map.canMoveTo(new Vector2d(6, 3)));
    }

    @Test
    void MoveWhenPossibleAndNotMoveWhenNotPossible(){

        //given
        Animal animal = new Animal(new Vector2d(2, 3));
        Animal otherAnimal = new Animal(new Vector2d(2,2));

        //when
        try {
            map.place(animal);
            map.place(otherAnimal);
        } catch (IncorrectPositionException e) {
            fail("Eexception while placing animal: " + e.getMessage());
        }
        map.move(otherAnimal, MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(2, 2), otherAnimal.getCurrentPosition());

        //when
        map.move(otherAnimal, MoveDirection.RIGHT);
        map.move(otherAnimal, MoveDirection.FORWARD);

        //then
        assertEquals(new Vector2d(3, 2), otherAnimal.getCurrentPosition());
        assertTrue(map.isOccupied(new Vector2d(3, 2)));

    }

    @Test
    void  IsPlaceOccupiedAndObjectAtPlace(){
        //given
        Animal animal = new Animal(new Vector2d(3, 3));

        //when
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            fail("Eexception while placing animal: " + e.getMessage());
        }


        //then
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
        assertFalse(map.isOccupied(new Vector2d(3, 2)));

        assertEquals(animal, map.objectAt(new Vector2d(3, 3)));
        assertNull(map.objectAt(new Vector2d(3, 2)));

    }


}