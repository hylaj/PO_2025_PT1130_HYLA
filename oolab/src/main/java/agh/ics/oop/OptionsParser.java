package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {

    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] temp = new MoveDirection[args.length];
        int counter = 0;

        for (String arg : args) {
            switch (arg){
                case "f" -> temp[counter++]=MoveDirection.FORWARD;
                case "b" -> temp[counter++]=MoveDirection.BACKWARD;
                case "r" -> temp[counter++]=MoveDirection.RIGHT;
                case "l" -> temp[counter++]=MoveDirection.LEFT;
            }
        }

        MoveDirection[] moves = new MoveDirection[counter];
        System.arraycopy(temp, 0, moves, 0, counter);  // create new array in order to get rid of 'null' values at the end of temporary array

        return moves;
    }
}
