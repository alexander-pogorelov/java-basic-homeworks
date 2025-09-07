package ru.otus.java.basic.homeworks.homework5;

public class Dog extends Animal{
    public Dog(String name, int endurance, int runningSpeed, int swimmingSpeed) {
        super(name, endurance, runningSpeed, swimmingSpeed);
        this.swimmingEnduranceCost = 2;
    }

    @Override
    public void info() {
        System.out.printf("Животное: собака, " + super.animalInfo());
    }
}
