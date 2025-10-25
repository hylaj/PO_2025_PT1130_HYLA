package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] args) {

        // implementacja listy - najlepszy wybór to ArrayList, ponieważ
        // lista jest tworzona raz, a potem tylko iterowana i odczytywana
        // , a ArrayList zapewnia bardzo szybki dostęp po indeksie i efektywną iterację
        List<MoveDirection> moves = new ArrayList<>();

        if (args == null) {  // zabezpieczenie przed NullPointerException
            return moves;
        }

        for (String arg : args) {
            switch (arg){
                case "f" -> moves.add(MoveDirection.FORWARD);
                case "b" -> moves.add(MoveDirection.BACKWARD);
                case "r" -> moves.add(MoveDirection.RIGHT);
                case "l" -> moves.add(MoveDirection.LEFT);
            }
        }

        return moves;
    }
}
