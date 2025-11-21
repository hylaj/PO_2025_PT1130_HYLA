package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    private final GrassField map = new GrassField(10);

    @Test
    void canBePlacedOnEmptyField() {
        //given
        Animal animal = new Animal(new Vector2d(2, 3));

        //when
        map.place(animal);

        //then
        assertTrue(map.isOccupied(new Vector2d(2, 3)));

    }

    @Test
    void cannotBePlacedOnOccupiedField(){

        //given
        Animal animal = new Animal(new Vector2d(2, 3));
        Animal otherAnimal = new Animal(new Vector2d(2,3));

        //when&then
        assertTrue(map.place(animal));
        assertFalse(map.place(otherAnimal));

    }

    @Test
    void canMoveToInsideMap(){
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
        assertTrue(map.canMoveTo(new Vector2d(2,2)));
    }


    @Test
    void MoveWhenPossibleAndNotMoveWhenNotPossible(){

        //given
        Animal animal = new Animal(new Vector2d(2, 3));
        Animal otherAnimal = new Animal(new Vector2d(2,2));

        //when
        map.place(animal);
        map.place(otherAnimal);
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
        map.place(animal);

        //then
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
        assertFalse(map.isOccupied(new Vector2d(3, 2)));

        assertEquals(animal, map.objectAt(new Vector2d(3, 3)));
        assertNull(map.objectAt(new Vector2d(3, 2)));

    }

}