package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void shouldMoveWhenValidInputGiven() {
        //given
        String []args = {"f", "b", "r", "f", "f", "l", "b", "b"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map = new RectangularMap(4,4);
        Simulation simulation = new Simulation(positions, directions,map);
        List<Animal> animals = simulation.getAnimals();

        //when
        simulation.run();

        //then
        assertEquals(new Vector2d(2,3), animals.get(0).getCurrentPosition());
        assertEquals(MapDirection.EAST, animals.get(0).getCurrentDirection());

        assertEquals(new Vector2d(4,4), animals.get(1).getCurrentPosition());
        assertEquals(MapDirection.WEST, animals.get(1).getCurrentDirection());

    }

    @Test
    void shouldNotMoveWhenInvalidInputGiven() {
        String[] args = {"x", "13", "z", "??", "hello"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(1, 1), new Vector2d(0, 0));
        WorldMap map = new RectangularMap(4,4);
        Simulation simulation = new Simulation(positions, directions, map);
        List<Animal> animals = simulation.getAnimals();

        // when
        simulation.run();

        //then
        assertEquals(new Vector2d(1, 1), animals.get(0).getCurrentPosition());
        assertEquals(MapDirection.NORTH, animals.get(0).getCurrentDirection());

        assertEquals(new Vector2d(0, 0), animals.get(1).getCurrentPosition());
        assertEquals(MapDirection.NORTH, animals.get(1).getCurrentDirection());


    }

    @Test
    void shouldHandleMixedValidAndInvalidInput() {

        // given
        String[] args = {"f", "abc", "b", "r", "jump", "f", "f","l", "b", "left", "b", "???"};
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map = new RectangularMap(4,4);
        Simulation simulation = new Simulation(positions, directions, map);
        List<Animal> animals = simulation.getAnimals();

        // when
        simulation.run();

        //then
        assertEquals(new Vector2d(2,3), animals.get(0).getCurrentPosition());
        assertEquals(MapDirection.EAST, animals.get(0).getCurrentDirection());

        assertEquals(new Vector2d(4,4), animals.get(1).getCurrentPosition());
        assertEquals(MapDirection.WEST, animals.get(1).getCurrentDirection());

    }
}