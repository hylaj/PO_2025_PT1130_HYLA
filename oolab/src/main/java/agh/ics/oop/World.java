package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.List;

public class World {

    static void main(String[] args) {
        System.out.println("System wystartowal");

        try{

            //test Simulation and GrassField

            AbstractWorldMap grassMap = new GrassField(10);
            List<MoveDirection> directions = OptionsParser.parse(args);
            grassMap.addObserver(new ConsoleMapDisplay());


            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
            Simulation simulation = new Simulation(positions, directions, grassMap);
            simulation.run();
        }
        catch (IllegalArgumentException e){
            System.out.println("Illegal argument given:" + e.getMessage());
        }

        System.out.println("System zakonczyl dzialanie");

    }

}
