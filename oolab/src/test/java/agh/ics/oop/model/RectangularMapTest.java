package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void canBePlacedOnEmptyField() {
        //given
        Animal animal = new Animal(new Vector2d(2, 3));
        WorldMap map = new RectangularMap(4, 4);

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
        WorldMap map = new RectangularMap(4, 4);

        //when&then
        assertTrue(map.place(animal));
        assertFalse(map.place(otherAnimal));

    }

}