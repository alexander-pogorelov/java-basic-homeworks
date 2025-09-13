package ru.otus.java.basic.homeworks.homework6;

public class Homework6 {
    public static void main(String[] args) {
        Bowl bowl = new Bowl(100);
        Cat[] cats = {
                new Cat("Cat1", 5),
                new Cat("Cat2", 20),
                new Cat("Cat3", 50),
                new Cat("Cat4", 10),
        };

        feedCats(cats, bowl);
        printCatsStatus(cats);
        bowl.printStatus();
        printDelimiter();

        feedCats(cats, bowl);
        printCatsStatus(cats);
        bowl.printStatus();
        printDelimiter();

        bowl.addFood(50);
        feedCats(cats, bowl);
        printCatsStatus(cats);
        bowl.printStatus();
        printDelimiter();
    }

    private static void feedCats(Cat[] cats, Bowl bowl) {
        for (Cat cat : cats) {
            cat.eat(bowl);
        }
    }

    private static void printCatsStatus(Cat[] cats) {
        for (Cat cat : cats) {
            System.out.println(cat);
        }
    }

    private static void printDelimiter() {
        System.out.println("---------------------------------");
    }
}
