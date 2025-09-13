package ru.otus.java.basic.homeworks.homework7;

public class Homework7 {
    public static void main(String[] args) {
        Human human = new Human("Вася");
        Horse horse = new Horse("Быстрый", 1000, 5);
        Bicycle bicycle = new Bicycle("Аист");
        ATV atv = new ATV("Шерп", 1000, 3);
        Car car = new Car("Лада Веста", 500, 1);

        boolean success1 = human.move(Terrain.SWAMP, 200);
        boolean success2 = human.move(Terrain.FOREST, 200);
        human.takeTransport(horse);
        human.takeTransport(car);
        boolean success3 = human.move(Terrain.SWAMP, 500);
        boolean success4 = human.move(Terrain.FOREST, 500);
        boolean success5 = human.move(Terrain.FOREST, 100);
        boolean success6 = human.move(Terrain.PLAIN, 100);
        human.leaveTransport();
        human.takeTransport(bicycle);
        boolean success7 = human.move(Terrain.SWAMP, 500);
        boolean success8 = human.move(Terrain.FOREST, 1500);
        boolean success9 = human.move(Terrain.PLAIN, 3000);
        human.leaveTransport();
        human.takeTransport(car);
        boolean success10 = human.move(Terrain.SWAMP, 500);
        boolean success11 = human.move(Terrain.FOREST, 500);
        boolean success12 = human.move(Terrain.PLAIN, 500);
        boolean success13 = human.move(Terrain.PLAIN, 500);
        human.leaveTransport();
        human.takeTransport(atv);
        boolean success14 = human.move(Terrain.SWAMP, 100);
        boolean success15 = human.move(Terrain.FOREST, 100);
        boolean success16 = human.move(Terrain.PLAIN, 100);
        human.leaveTransport();
        boolean success17 = human.move(Terrain.PLAIN, 200);
    }
}
