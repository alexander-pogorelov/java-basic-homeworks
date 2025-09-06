package ru.otus.java.basic.homeworks.homework4;

public class User {
    private final String surname;
    private final String name;
    private final String patronymic;
    private final int birthYear;
    private final String email;

    public User(String surname, String name, String patronymic, int birthYear, String email) {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.birthYear = birthYear;
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getEmail() {
        return email;
    }

    public void info() {
        System.out.printf("ФИО: %s %s %s%n", surname, name, patronymic);
        System.out.printf("Год рождения: %d%n", birthYear);
        System.out.printf("e-mail: %s%n", email);
    }
}
