package ru.otus.java.basic.homeworks.homework13.Service;

import ru.otus.java.basic.homeworks.homework13.Application;
import ru.otus.java.basic.homeworks.homework13.ApplicationException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Calculator implements Application {
    private final List<String> operations = Arrays.asList("+", "-", "*", "/");
    private final String delimiter = "|";
    private Integer firstNumber;
    private Integer secondNumber;
    private String operation;

    public String getName() {
        return "Калькулятор";
    }

    public String getPrompt() {
        return String.format(
                "Доступные операции: %s%nВведите первое целое число, знак операции, второе целое число. Используйте разделитель: %s",
                operations,
                delimiter
        );
    }

    public String run(String input) throws ApplicationException {
        try {
            String[] parts = parse(input);
            validate(parts);
            double result = calculate();
            clear();
            return String.valueOf(result);
        } catch (WrongArgumentListException | WrongArgumentException | WrongOperationException | DivisionByZeroException e) {
            clear();
            throw new ApplicationException(e.getMessage());
        }
    }

    private String[] parse(String input) {
        return input.split(Pattern.quote(delimiter), 3);
    }

    public void validate(String[] parts) throws WrongOperationException, WrongArgumentException, DivisionByZeroException, WrongArgumentListException {
        if (parts.length != 3) {
            throw new WrongArgumentListException();
        }
        if (!operations.contains(parts[1].trim())) {
            throw new WrongOperationException(parts[1]);
        }
        operation = parts[1].trim();
        try {
            firstNumber = Integer.parseInt(parts[0].trim());
        } catch (NumberFormatException e) {
            throw new WrongArgumentException(parts[0].trim());
        }
        try {
            secondNumber = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new WrongArgumentException(parts[2].trim());
        }
        if ("/".equals(operation) && secondNumber == 0) {
            throw new DivisionByZeroException(parts[2].trim());
        }
    }

    private double calculate() {
        switch (operation) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return (double) firstNumber / secondNumber;
            default:
                return 0;
        }
    }

    private void clear() {
        firstNumber = null;
        secondNumber = null;
        operation = null;
    }
}
