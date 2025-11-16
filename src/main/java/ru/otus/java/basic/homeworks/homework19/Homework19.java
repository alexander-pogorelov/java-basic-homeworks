package ru.otus.java.basic.homeworks.homework19;

public class Homework19 {
    public static void main(String[] args) {
        Apple apple1 = new Apple(0.2);
        Apple apple2 = new Apple(0.25);
        Apple apple3 = new Apple(0.23);

        Box<Apple> appleBox = new Box<>();
        appleBox.add(apple1);
        appleBox.add(apple2);
        appleBox.add(apple3);
        printBoxItems(appleBox, "appleBox");
        printBoxWeight(appleBox, "appleBox");

        Orange orange1 = new Orange(0.3);
        Orange orange2 = new Orange(0.35);
        Orange orange3 = new Orange(0.4);

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(orange1);
        orangeBox.add(orange2);
        orangeBox.add(orange3);
        printBoxItems(orangeBox,  "orangeBox");
        printBoxWeight(orangeBox,  "orangeBox");

        printDelimiter();

        printBoxItems(appleBox, "appleBox");
        printBoxItems(orangeBox, "orangeBox");
        printBoxComparing(appleBox, orangeBox);

        printDelimiter();

        Box<Fruit> fruitBox1 = new Box<>();
        fruitBox1.add(apple1);
        fruitBox1.add(apple2);
        fruitBox1.add(apple3);
        fruitBox1.add(orange1);
        fruitBox1.add(orange2);
        fruitBox1.add(orange3);
        printBoxItems(fruitBox1,  "fruitBox1");
        printBoxWeight(fruitBox1,  "fruitBox1");

        printDelimiter();

        Box<Fruit> fruitBox2 = new Box<>();
        fruitBox2.add(apple1);
        fruitBox2.add(apple2);
        fruitBox2.add(apple3);

        printBoxItems(appleBox, "appleBox");
        printBoxItems(fruitBox2, "fruitBox2");
        printBoxComparing(appleBox, fruitBox2);

        printDelimiter();

        System.out.println("До перемещения");
        printBoxItems(appleBox, "appleBox");
        appleBox.transferTo(appleBox);
        System.out.println("После перемещения");
        printBoxItems(appleBox, "appleBox");

        printDelimiter();

        System.out.println("До перемещения");
        printBoxItems(appleBox, "appleBox");
        printBoxItems(fruitBox1,  "fruitBox1");
        appleBox.transferTo(fruitBox1);
        System.out.println("После перемещения");
        printBoxItems(appleBox, "appleBox");
        printBoxItems(fruitBox1,  "fruitBox1");

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.add(apple1);
        appleBox2.add(apple2);
        appleBox2.add(apple3);

        printDelimiter();

        System.out.println("До перемещения");
        printBoxItems(appleBox2, "appleBox2");
        printBoxItems(appleBox, "appleBox");
        appleBox2.transferTo(appleBox);
        System.out.println("После перемещения");
        printBoxItems(appleBox2, "appleBox2");
        printBoxItems(appleBox, "appleBox");


    }

    private static void printBoxWeight(Box<?> box, String name) {
        System.out.printf("Вес коробки %s равен %f%n", name, box.weight());
    }

    private static void printBoxComparing(Box<?> box1, Box<?> box2) {
        System.out.printf("Вес обоих коробок одинаковый: %b%n", box1.compare(box2));
    }

    private static void printBoxItems(Box<?> box, String name) {
        System.out.printf("Содержимое коробки %s: %s%n", name, box.getItems());
    }

    private static void printDelimiter() {
        System.out.println("------------------------------------------------------------------------------------");
    }
}
