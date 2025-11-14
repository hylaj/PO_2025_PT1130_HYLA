package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testToString() {
    }


    @Test
    void animalShouldMoveToCorrectPosition()  {
        //given
        Animal animal = new Animal(new Vector2d(2, 2));
        WorldMap map = new RectangularMap(4, 4);

        //when
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.RIGHT, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.LEFT, map);
        animal.move(MoveDirection.BACKWARD, map);

        //then
        assertTrue(animal.isAt(new Vector2d(3,2)));
    }

    @Test
    void animalShouldHaveCorrectDirection(){
        //given
        Animal animal = new Animal(new Vector2d(2, 2));
        WorldMap map = new RectangularMap(4, 4);

        //when
        animal.move(MoveDirection.LEFT, map);
        animal.move(MoveDirection.FORWARD, map);

        //then
        assertEquals(MapDirection.WEST, animal.getCurrentDirection());
    }
}