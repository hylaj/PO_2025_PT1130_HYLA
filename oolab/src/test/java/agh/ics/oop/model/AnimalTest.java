package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testToString() {
    }

    @Test
    void animalShouldNotMoveOutsideMap() {
        //given
        Animal animal = new Animal(new Vector2d(4, 4));

        //when
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);

        //then
        assertTrue(animal.getCurrentPosition().precedes(Animal.UPPER_RIGHT) && animal.getCurrentPosition().follows(Animal.LOWER_LEFT));
        assertTrue(animal.isAt(new Vector2d(3,4)));
    }

    @Test
    void animalShouldMoveToCorrectPosition()  {
        //given
        Animal animal = new Animal(new Vector2d(2, 2));

        //when
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);

        //then
        assertTrue(animal.isAt(new Vector2d(3,2)));
    }

    @Test
    void animalShouldHaveCorrectDirection(){
        //given
        Animal animal = new Animal(new Vector2d(2, 2));

        //when
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);

        //then
        assertEquals(MapDirection.WEST, animal.getCurrentDirection());
    }
}