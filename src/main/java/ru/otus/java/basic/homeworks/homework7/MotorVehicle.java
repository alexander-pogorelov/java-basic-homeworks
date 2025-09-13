package ru.otus.java.basic.homeworks.homework7;

public abstract class MotorVehicle implements Transport {
    protected final String model;
    protected final int totalFuelAmount;
    protected int currentFuelAmount;
    protected final int fuelConsumption;

    public MotorVehicle(String model, int totalFuelAmount, int fuelConsumption) {
        this.model = model;
        this.totalFuelAmount = totalFuelAmount;
        this.currentFuelAmount = totalFuelAmount;
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public boolean move(Terrain terrain, int distance) {
        int requiredFuelAmount = calculateRequiredFuelAmount(distance);
        if (requiredFuelAmount > currentFuelAmount) {
            System.out.printf("%s не смог проехать %d через %s. Не хватает топлива.%n", getName(), distance, terrain.getRussian());
            return false;
        }
        currentFuelAmount -= requiredFuelAmount;
        System.out.printf("%s успешно проехал %d через %s. Осталось топлива: %d.%n", getName(), distance, terrain.getRussian(), currentFuelAmount);
        return true;
    }

    public void addFuel(int fuelAmount) {
        currentFuelAmount += fuelAmount;
        if (currentFuelAmount > totalFuelAmount) {
            currentFuelAmount = totalFuelAmount;
        }
    }

    protected int calculateRequiredFuelAmount(int distance) {
        return distance * fuelConsumption;
    }
}
