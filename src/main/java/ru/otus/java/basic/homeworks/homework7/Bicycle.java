package ru.otus.java.basic.homeworks.homework7;

public class Bicycle implements Transport {
    private final String model;

    public Bicycle(String model) {
        this.model = model;
    }

    @Override
    public String getName() {
        return String.format("велосипед '%s'", model);
    }

    @Override
    public boolean move(Terrain terrain, int distance) {
        switch (terrain) {
            case Terrain.SWAMP:
                System.out.printf("%s не может ехать через %s.%n", getName(), terrain.getRussian());
                return false;
            default:
                System.out.printf("%s успешно проехал %d через %s.%n", getName(), distance, terrain.getRussian());
                return true;
        }
    }
}
