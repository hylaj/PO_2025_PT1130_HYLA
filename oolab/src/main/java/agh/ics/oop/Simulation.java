package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    WorldMap worldMap;

    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, WorldMap  worldMap) {
        this.moves = moves;
        this.worldMap = worldMap;
        animals = new ArrayList<>(); // implementacja listy - najlepszy wybór to ArrayList, ponieważ zapewnia szybki dostęp do elementów po indeksie (np. w metodzie run). W naszej klasie Simulation dane dodawane są tylko raz (nie usuwamy i nie dodajemy nowych elementów w trakcie), wiec ArrayList jest bardzoej wydajna niz LinkedList

        for  (Vector2d position : positions) {
            Animal animal = new Animal(position);

            if(worldMap.place(animal)) {
                animals.add(animal);
            }
        }
    }

    public void run(){

        for (int i=0; i<moves.size(); i++) {
            int index=i%animals.size();
            Animal animal = animals.get(index);

            worldMap.move(animal, moves.get(i));
            System.out.println(worldMap);
        }
    }
}
