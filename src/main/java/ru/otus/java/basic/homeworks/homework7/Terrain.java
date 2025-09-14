package ru.otus.java.basic.homeworks.homework7;

public enum Terrain {
    FOREST("густой лес"), PLAIN("равнина"), SWAMP("болото");

    private final String russian;

    Terrain(String russian) {
        this.russian = russian;
    }

    public String getRussian() {
        return russian;
    }
}
