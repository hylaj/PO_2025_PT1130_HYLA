package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        this.moves = moves;

        // implementacja listy - najlepszy wybór to ArrayList,
        // ponieważ zapewnia szybki dostęp do elementów po indeksie (np. w metodzie run),
        // W naszej klasie Simulation dane dodawane są tylko raz (nie usuwamy i nie dodajemy nowych elementów w trakcie),
        // wiec ArrayList jest bardzoej wydajna niz LinkedList

        animals = new ArrayList<>();

        for  (Vector2d position : positions) {
            animals.add(new Animal(position));
        }
    }

    public void run(){

        for (int i=0; i<moves.size(); i++) {
            int index=i%animals.size();
            Animal animal = animals.get(index);

            animal.move(moves.get(i));
            System.out.println("Zwierze "+ index + ": " + animal.getCurrentPosition());
        }
    }
}
