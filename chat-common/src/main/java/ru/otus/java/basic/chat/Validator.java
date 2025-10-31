package ru.otus.java.basic.chat;

public class Validator {
    public static void validateLogin(String login) throws ValidationException {
        if (login.length() < 3) {
            throw new ValidationException("Логин должен содержать 3+ символов");
        }
        if (!login.toLowerCase().matches("[a-z]+")) {
            throw new ValidationException("Логин должен состоять только из латинских букв");
        }
    }

    public static void validatePassword(String password) throws ValidationException {
        if (password.length() < 3) {
            throw new ValidationException("Пароль должен содержать 3+ символов");
        }
    }
}
