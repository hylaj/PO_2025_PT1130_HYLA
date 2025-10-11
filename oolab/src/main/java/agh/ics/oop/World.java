package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");
        run(args);
        System.out.println("System zakonczyl dzialanie");
    }

    public static void run(String[] args) {
        System.out.println("Start");

        for (String arg : args) {
            switch (arg) {
                case "f" -> System.out.println("Zwierzak idzie do przodu");
                case "b" -> System.out.println("Zwierzak idzie do tyłu");
                case "r" -> System.out.println("Zwierzak skręca w prawo");
                case "l" -> System.out.println("Zwierzak skręca w lewo");
            }
        }

        System.out.println("Stop");
    }
}
