package ru.otus.java.basic.homeworks.homework13.Service;

public class WrongArgumentListException extends Exception {
    public WrongArgumentListException() {
        super("Неверное число аргументов");
    }
}
