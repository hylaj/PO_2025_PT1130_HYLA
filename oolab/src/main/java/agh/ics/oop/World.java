package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {

    static void main(String[] args) {
        System.out.println("System wystartowal");
        run(OptionsParser.parse(args));

        // test klasy Vector2d
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));


        //test MapDirection
        MapDirection dir = MapDirection.NORTH;
        System.out.println("Kierunek: " + dir);
        System.out.println("Następny: " + dir.next());
        System.out.println("Poprzedni: " + dir.previous());
        System.out.println("Wektor: " + dir.toUnitVector());

        //test Animal
        Animal zwierze = new Animal();
        System.out.println(zwierze);
        WorldMap map = new RectangularMap(5, 5);
        map.move(zwierze, MoveDirection.BACKWARD);

        //test Simulation
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();


        System.out.println("System zakonczyl dzialanie");

    }

    public static void run(List<MoveDirection> directions) {

        for (MoveDirection direction : directions) {
            switch (direction) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }
}
