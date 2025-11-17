package ru.otus.java.basic.homeworks.homework19;

public class Orange extends Fruit {
    public Orange(double itemWeight) {
        super(itemWeight);
    }

    @Override
    public String toString() {
        return "Orange{" +
                "itemWeight=" + itemWeight +
                '}';
    }
}
