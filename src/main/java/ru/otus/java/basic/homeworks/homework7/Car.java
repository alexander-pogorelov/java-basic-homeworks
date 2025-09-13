package ru.otus.java.basic.homeworks.homework7;

public class Car extends MotorVehicle {
    public Car(String model, int fuelAmount, int fuelConsumption) {
        super(model, fuelAmount, fuelConsumption);
    }

    @Override
    public String getName() {
        return String.format("автомобиль '%s'", model);
    }

    @Override
    public boolean move(Terrain terrain, int distance) {
        switch(terrain) {
            case Terrain.FOREST:
            case Terrain.SWAMP:
                System.out.printf("%s не может ехать через %s.%n", getName(), terrain.getRussian());
                return false;
            default:
                return super.move(terrain, distance);
        }
    }
}
