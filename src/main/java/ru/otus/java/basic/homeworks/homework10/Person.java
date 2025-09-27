package ru.otus.java.basic.homeworks.homework10;

import java.util.Objects;

public class Person {
    private final String surname;
    private final String name;

    public Person(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person {" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(surname, person.surname) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name);
    }
}
