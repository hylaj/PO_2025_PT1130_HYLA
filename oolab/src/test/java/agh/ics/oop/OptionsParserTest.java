package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parserShouldReturnValidCommands(){
        //given
        String[] input = {"f", "b", "r", "l"};

        //when
        List<MoveDirection> result = OptionsParser.parse(input);

        //then
        List<MoveDirection> expected = List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        );

        assertEquals(expected, result);

    }

    @Test
    void parserShouldThrowExceptionWhenInvalidInputProvided(){

        //given
        String[] input = {"x", "g", "z", "123", "forward"};

        //when&then
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(input));
    }

    @Test
    void parserShouldReturnEmptyListWhenNullProvided(){

        // given
        String[] input = null;

        //when
        List<MoveDirection> result = OptionsParser.parse(input);

        //then
        assertTrue(result.isEmpty());
    }

}