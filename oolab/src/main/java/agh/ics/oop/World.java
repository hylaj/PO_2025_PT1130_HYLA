package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {

    static void main(String[] args) {
        System.out.println("System wystartowal");
        run(OptionsParser.parse(args));
        System.out.println("System zakonczyl dzialanie");
    }

    public static void run(MoveDirection[] directions) {
        System.out.println("Start");

        for (MoveDirection direction : directions) {
            switch (direction) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
            }
        }
        System.out.println("Stop");
    }
}
