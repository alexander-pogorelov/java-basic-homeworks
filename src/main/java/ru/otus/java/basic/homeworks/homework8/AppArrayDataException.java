package ru.otus.java.basic.homeworks.homework8;

public class AppArrayDataException extends Exception {
    public AppArrayDataException(String element, int row, int column) {
        super(String.format("Элемент '%s' в строке %d и столбце %d не может быть преобразовн к числу.", element, row, column));
    }
}
