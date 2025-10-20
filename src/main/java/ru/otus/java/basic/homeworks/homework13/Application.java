package ru.otus.java.basic.homeworks.homework13;

public interface Application {
    String getName();
    String getPrompt();
    String run(String input) throws ApplicationException;
}
