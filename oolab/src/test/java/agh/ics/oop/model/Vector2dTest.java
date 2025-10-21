package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void areTwoVectors2dEqual(){
        //given
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(2, 3);
        Vector2d v3 = new Vector2d(3, 2);

        //when & then
        assertTrue(v1.equals(v2));
        assertFalse(v1.equals(v3));
    }

    @Test
    void convertVector2dToString(){

        //given
        Vector2d v1 = new Vector2d(2, 3);

        //when
        String v1ToString=v1.toString();

        //then
        assertEquals("(2, 3)", v1ToString);
    }

    @Test
    void doesOtherVector2dPrecede(){

        //given
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(3, 4);
        Vector2d v3 = new Vector2d(1, 1);
        Vector2d v4 = new Vector2d(2, 3);

        //when & then
        assertTrue(v1.precedes(v2));
        assertTrue(v1.precedes(v4));
        assertFalse(v1.precedes(v3));
    }

    @Test
    void doesOtherVector2dFollow(){
        //given
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(3, 4);
        Vector2d v3 = new Vector2d(1, 1);
        Vector2d v4 = new Vector2d(2, 3);

        //when & then
        assertTrue(v1.follows(v3));
        assertTrue(v1.follows(v4));
        assertFalse(v1.follows(v2));
    }

    @Test
    void upperRightReturnMaxVector2d(){
        //given
        Vector2d v = new Vector2d(2, 4);
        Vector2d other = new Vector2d(3, 3);

        //when
        Vector2d vUpperRight = v.upperRight(other);

        //then
        assertEquals(new Vector2d(3,4), vUpperRight);
    }

    @Test
    void lowerLeftReturnMinVector2d(){
        //given
        Vector2d v = new Vector2d(2, 4);
        Vector2d other = new Vector2d(3, 3);

        //when
        Vector2d vLowerLeft = v.lowerLeft(other);

        //then
        assertEquals(new Vector2d(2, 3),  vLowerLeft);
    }


    @Test
    void addReturnSumOfTwoVectors2d(){
        //given
        Vector2d v = new Vector2d(2, 3);
        Vector2d other = new Vector2d(3, 4);

        //when
        Vector2d sum = v.add(other);

        //then
        assertEquals(new Vector2d(5,7), sum);
    }

    @Test
    void subtractReturnDifferenceOfTwoVectors(){

        //given
        Vector2d v = new Vector2d(2, 3);
        Vector2d other = new Vector2d(3, 4);

        //when
        Vector2d difference = v.subtract(other);

        //then
        assertEquals(new Vector2d(-1,-1), difference);
    }

    @Test
    void createOppositeVector2d(){
        //given
        Vector2d v = new Vector2d(2, 3);

        //when
        Vector2d opposite = v.opposite();

        //then
        assertEquals(new Vector2d(-2,-3), opposite);
    }
}