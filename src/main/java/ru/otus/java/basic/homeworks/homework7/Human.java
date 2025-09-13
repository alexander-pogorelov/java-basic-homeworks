package ru.otus.java.basic.homeworks.homework7;

public class Human {
    private final String name;
    private Transport currentTransport;

    public Human(String name) {
        this.name = name;
    }

    public void takeTransport(Transport transport) {
        if (currentTransport == null) {
            this.currentTransport = transport;
            System.out.printf("%s взял транспорт %s.%n", name, transport.getName());
            return;
        }
        System.out.printf("%s не может взять транспорт %s. %s уже взял транспорт %s.%n", name, transport.getName(), name, currentTransport.getName());
    }

    public void leaveTransport() {
        if (currentTransport != null) {
            currentTransport = null;
        }
    }

    public boolean move(Terrain terrain, int distance) {
        if (currentTransport == null) {
            return walk(terrain, distance);
        }
        return currentTransport.move(terrain, distance);
    }

    private boolean walk(Terrain terrain, int distance) {
        System.out.printf("Человек прошел дистанцию %d по местности '%s'.%n", distance, terrain.getRussian());
        return true;
    }
}
