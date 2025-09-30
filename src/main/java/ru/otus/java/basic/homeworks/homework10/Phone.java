package ru.otus.java.basic.homeworks.homework10;

import java.util.Objects;

public class Phone {
    private final long number;

    public Phone(long number) {
        this.number = number;
    }

    public long getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "" + getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return number == phone.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
