package ru.otus.java.basic.http.server.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemsStorage {
    private static List<Item> items;

    public static void init() {
        items = new ArrayList<>(Arrays.asList(
                new Item(1L, "Milk", 80),
                new Item(2L, "Bread", 40),
                new Item(3L, "Cheese", 400)
        ));
    }

    public static List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public static void createItem(Item item) {
        item.setId(items.stream().mapToLong(Item::getId).max().orElse(0) + 1);
        items.add(item);
    }

    public static void deleteItem(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                items.remove(item);
                break;
            }
        }
    }

    public static Item getItem(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
