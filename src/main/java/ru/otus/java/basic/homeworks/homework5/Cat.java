package ru.otus.java.basic.homeworks.homework5;

public class Cat extends Animal{
    public Cat(String name, int endurance, int runningSpeed) {
        super(name, endurance, runningSpeed, 0);
        this.swimmingEnduranceCost = 0;
    }

    @Override
    public void info() {
        System.out.printf("Животное: кот, " + super.animalInfo());
    }

    @Override
    public int swim(int distance) {
        System.out.println("Коты не умеют плавать.");

        return Animal.FAILED_TIME;
    }
}
