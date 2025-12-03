package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {

    private final ArrayList<Simulation> simulations;
    private final List<Thread> threads = new ArrayList<>();


    public SimulationEngine(ArrayList<Simulation> simulations){
        this.simulations = simulations;
    }

    public void runSync(){
        for (Simulation simulation : simulations){
            simulation.run();
        }
    }

    public void runAsync(){
        for (Simulation simulation : simulations){
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }
    }

    public void awaitSimulationsEnd() throws InterruptedException {

            for (Thread thread : threads) {
                thread.join();
            }
    }


}
