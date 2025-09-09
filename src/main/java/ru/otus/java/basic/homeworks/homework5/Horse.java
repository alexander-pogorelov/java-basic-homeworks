package ru.otus.java.basic.homeworks.homework5;

public class Horse extends Animal{
    public Horse(String name, int endurance, int runningSpeed, int swimmingSpeed) {
        super(name, endurance, runningSpeed, swimmingSpeed);
        this.swimmingEnduranceCost = 4;
    }

    @Override
    public void info() {
        System.out.printf("Животное: лошадь, " + super.animalInfo());
    }
}
