package ru.otus.java.basic.homeworks.homework5;

public class Homework5 {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Barsik", 1000, 5);
        Dog dog1 = new Dog("Polkan", 5000, 10, 3);
        Horse horse1 = new Horse("Veterok", 15000, 40, 10);

        cat1.info();
        int time1 = cat1.swim(100);
        int time2 = cat1.run(500);
        int time3 = cat1.run(600);
        cat1.sleep();
        int time5 = cat1.run(5000);
        printLine();

        dog1.info();
        int time6 = dog1.run(2000);
        int time7 = dog1.swim(2000);
        dog1.sleep();
        int time8 = dog1.swim(2000);
        printLine();

        horse1.info();
        int time9 = horse1.run(10000);
        int time10 = horse1.swim(1000);
        int time11 = horse1.run(5000);
        horse1.sleep();
        int time12 = horse1.run(5000);
    }

    private static void printLine() {
        System.out.println("-------------------------------------------");
    }
}
