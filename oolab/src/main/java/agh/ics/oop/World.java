package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");
        run(args);
        System.out.println("System zakonczyl dzialanie");
    }
    public static void run(String[] args) {
        System.out.println("Zwierzak idzie do przodu");
        if (args.length > 0) {
            System.out.println("Przekazane argumenty:");
            System.out.println(String.join(", ", args));
        }
    }
}
