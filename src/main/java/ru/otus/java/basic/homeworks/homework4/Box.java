package ru.otus.java.basic.homeworks.homework4;

public class Box {
    private final int length;
    private final int width;
    private final int height;
    private String color;
    private String item = "";
    private boolean isOpened = false;

    public Box(int length, int width, int height, String color) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void changeColor(String color) {
        this.color = color;
        System.out.printf("Цвет коробки изменился на %s.%n", color);
    }

    public void open() {
        this.isOpened = true;
        System.out.println("Коробка открыта.");
    }

    public void close() {
        this.isOpened = false;
        System.out.println("Коробка закрыта.");
    }

    public void addItem(String item) {
        if (!this.isOpened) {
            System.out.println("Невозможно добавить предмет в закрытую коробку. Сначала нужно открыть коробку.");
            return;
        }
        if (item.isEmpty()) {
            System.out.println("Невозможно добавить пустой предмет.");
            return;
        }
        if (this.hasItem()) {
            System.out.printf("Невозможно добавить предмет '%s'. Коробка уже содержит '%s'.%n", item, this.item);
            return;
        }
        this.item = item;
        System.out.printf("В коробку добавлен предмет: '%s'.%n", item);
    }

    public void drop() {
        if (!this.hasItem()) {
            System.out.println("Невозможно извлечь предмет из пустой коробки.");
            return;
        }
        System.out.printf("Из коробки извлечен '%s'.%n", this.item);
        this.item = "";
    }

    public void info() {
        System.out.printf(
                "Коробка. Длина: %d, ширина: %d, высота: %d, цвет: %s, открыта: %s, содержит предмет: '%s'.%n",
                length,
                width,
                height,
                color,
                isOpened ? "да" : "нет",
                item.isEmpty() ? "нет" : item
        );
    }

    private boolean hasItem() {
        return !this.item.isEmpty();
    }
}
