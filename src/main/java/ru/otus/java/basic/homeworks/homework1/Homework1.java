package ru.otus.java.basic.homeworks.homework1;

import java.util.Random;
import java.util.Scanner;

public class Homework1 {
    public static void main(String[] args) {
        System.out.println("Please enter the number from 1 to 5 to choose the method to execute");
        System.out.println("Or enter 0 to execute all methods:");
        Scanner scanner = new Scanner(System.in);
        int input;

        try {
            input = scanner.nextInt();
        } catch (Throwable e) {
            System.out.printf("Not valid data was entered %s%n", e.getMessage() != null ? e.getMessage() : "");

            return;
        }

        if (input > 5 || input < 0) {
            System.out.printf("Valid numbers from 0 to 5. Entered: %s.%n", input);

            return;
        }

        System.out.println("Введено: " + input);
        printLine();

        Random random = new Random();

        switch (input) {
            case 1:
                greetings();
                break;
            case 2:
                checkSign(generateRandomInteger(random), generateRandomInteger(random), generateRandomInteger(random));
                break;
            case 3:
                selectColor();
                break;
            case 4:
                compareNumbers();
                break;
            case 5:
                addOrSubtractAndPrint(generateRandomInteger(random), generateRandomInteger(random), generateRandomBoolean());
                break;
            case 0:
            default:
                runAllMethods();
        }
    }

    public static void runAllMethods() {
        greetings();
        checkSign(1, 2, 3);
        checkSign(-1, -2, 3);
        checkSign(-1, -2, -3);
        selectColor();
        compareNumbers();
        addOrSubtractAndPrint(10, 1, true);
        addOrSubtractAndPrint(10, 1, false);
    }

    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");

        printLine();
    }

    public static void checkSign(int a, int b, int c) {
        System.out.printf("a: %d%n", a);
        System.out.printf("b: %d%n", b);
        System.out.printf("c: %d%n", c);

        long result = a;
        result += b;
        result += c;

        System.out.printf("Сумма: %d%n", result);

        if (result >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }

        printLine();
    }

    public static void selectColor() {
        int data = 20;

        if (data <= 10) {
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }

        printLine();
    }

    public static void compareNumbers() {
        int a = 3;
        int b = 4;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }

        printLine();
    }

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        System.out.printf("initValue: %d%n", initValue);
        System.out.printf("delta: %d%n", delta);
        System.out.printf("increment: %b%n", increment);

        long result = initValue;

        if (increment) {
            result += delta;
        } else {
            result -= delta;
        }

        System.out.printf("Результат: %d%n", result);

        printLine();
    }

    public static void printLine() {
        System.out.println("-------------------------------------------");
    }

    public static int generateRandomInteger(Random random) {
        return random.nextInt();
    }

    public static boolean generateRandomBoolean() {
        return Math.random() < 0.5;
    }
}
