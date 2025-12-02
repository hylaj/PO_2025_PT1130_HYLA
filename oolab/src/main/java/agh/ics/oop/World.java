package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {

    static void main(String[] args) {
        System.out.println("System wystartowal");

        try{

            //test Simulation and GrassField

            AbstractWorldMap grassMap = new GrassField(10);
            AbstractWorldMap rectMap = new RectangularMap(5,5);

            ArrayList<Simulation> simulations = new ArrayList<>();

            List<MoveDirection> directions = OptionsParser.parse(args);
            grassMap.addObserver(new ConsoleMapDisplay());
            rectMap.addObserver(new ConsoleMapDisplay());



            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
            Simulation simulation_grass = new Simulation(positions, directions, grassMap);
            Simulation simulation_rect = new Simulation(positions, directions, rectMap);

            simulations.add(simulation_grass);
            simulations.add(simulation_rect);

            SimulationEngine simulation_engine = new SimulationEngine(simulations);
            simulation_engine.runSync();

        }
        catch (IllegalArgumentException e){
            System.out.println("Illegal argument given:" + e.getMessage());
        }

        System.out.println("System zakonczyl dzialanie");

    }

}
