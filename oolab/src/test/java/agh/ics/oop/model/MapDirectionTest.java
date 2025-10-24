package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void nextReturnCorrectDirection() {

        //given
        MapDirection north = MapDirection.NORTH;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;
        MapDirection east = MapDirection.EAST;

        // when
        MapDirection northNext = north.next();
        MapDirection southNext = south.next();
        MapDirection westNext = west.next();
        MapDirection eastNext = east.next();

        // then
        assertEquals(MapDirection.EAST, northNext);
        assertEquals(MapDirection.WEST, southNext);
        assertEquals(MapDirection.NORTH, westNext);
        assertEquals(MapDirection.SOUTH, eastNext);

    }

    @Test
    void previousReturnCorrectDirection() {

        //given
        MapDirection north = MapDirection.NORTH;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;
        MapDirection east = MapDirection.EAST;

        //when
        MapDirection northPrev = north.previous();
        MapDirection southPrev = south.previous();
        MapDirection westPrev = west.previous();
        MapDirection eastPrev = east.previous();

        //then
        assertEquals(MapDirection.WEST, northPrev);
        assertEquals(MapDirection.EAST, southPrev);
        assertEquals(MapDirection.NORTH, eastPrev);
        assertEquals(MapDirection.SOUTH, westPrev);
    }

}