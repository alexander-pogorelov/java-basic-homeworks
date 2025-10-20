package ru.otus.java.basic.homeworks.homework13.Service;

public class WrongOperationException extends Exception {
    public WrongOperationException(String operation) {
        super(String.format("Введенеа недопустимая операция '%s'", operation));
    }
}
