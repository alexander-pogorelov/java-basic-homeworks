package ru.otus.java.basic.homeworks.homework13.Service;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException(String argument) {
        super(String.format("Нельзя делить на %s", argument));
    }
}
