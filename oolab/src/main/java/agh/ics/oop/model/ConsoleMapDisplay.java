package agh.ics.oop.model;


public class ConsoleMapDisplay implements MapChangeListener{

    private int updateCounter = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message){
        updateCounter++;
        System.out.println("Map ID: " + worldMap.getId());
        System.out.println("Update No. " + updateCounter);
        System.out.println(message);
        System.out.println(worldMap);

    }

}
