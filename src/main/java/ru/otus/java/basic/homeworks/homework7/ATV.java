package ru.otus.java.basic.homeworks.homework7;

public class ATV extends MotorVehicle {
    public ATV(String model, int fuelAmount, int fuelConsumption) {
        super(model, fuelAmount, fuelConsumption);
    }

    @Override
    public String getName() {
        return String.format("вездеход '%s'", model);
    }
}
