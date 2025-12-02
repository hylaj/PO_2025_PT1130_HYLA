package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine {

    private final ArrayList<Simulation> simulations;

    public SimulationEngine(ArrayList<Simulation> simulations){
        this.simulations = simulations;
    }

    public void runSync(){
        for (Simulation simulation : simulations){
            simulation.run();
        }
    }
}
