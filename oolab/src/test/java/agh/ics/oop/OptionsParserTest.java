package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parserShouldReturnValidCommandsAndIgnoreInvalid(){
        //given
        String[] input = {"f", "b", "x", "r", "l", "g"};

        //when
        MoveDirection[] result = OptionsParser.parse(input);

        //then
        assertArrayEquals(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT}, result); // assertArrayEquals() sprawdza czy kazdy element tablicy ma ta sama wartosc i kolejnosc

    }
}