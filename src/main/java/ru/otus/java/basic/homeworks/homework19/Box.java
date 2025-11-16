package ru.otus.java.basic.homeworks.homework19;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Box <T extends Fruit> {
    private final List<T> items;

    public Box() {
        this.items = new ArrayList<>();
    }

    public List<T> getItems() {
        return items;
    }

    public void add(T item) {
        items.add(item);
    }

    public double weight() {
        double weight = 0;
        for (T item : items) {
            weight += item.getItemWeight();
        }
        return  weight;
    }

    public boolean compare(Box<?> anotherBox) {
        return Math.abs(weight() - anotherBox.weight()) < 0.00001;
    }

    public void transferTo(Box<? super T> anotherBox) {
        if (this == anotherBox) {
            return;
        }
        for (T item : items) {
            anotherBox.add(item);
        }
        items.clear();
    }
}
