package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        this.moves = moves;
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
