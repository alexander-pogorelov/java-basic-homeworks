package ru.otus.java.basic.homeworks.homework19;

public class Apple extends Fruit {
    public Apple(double itemWeight) {
        super(itemWeight);
    }

    @Override
    public String toString() {
        return "Apple{" +
                "itemWeight=" + itemWeight +
                '}';
    }
}
