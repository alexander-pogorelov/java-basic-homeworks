package ru.otus.java.basic.homeworks.homework10;

public class DuplicatePhoneException extends Exception{
    public DuplicatePhoneException(Phone phone) {
        super(String.format("Телефон %d уже есть в телефонной книге.", phone.getNumber()));
    }
}
