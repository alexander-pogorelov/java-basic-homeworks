package ru.otus.java.basic.homeworks.homework13.Service;

public class WrongArgumentException extends Exception {
    public WrongArgumentException(String argument) {
        super(String.format("Введенное число '%s' не является целым", argument));
    }
}
