package ru.otus.java.basic.homeworks.homework11;

import java.util.HashMap;
import java.util.Map;

public class PersonDataBase {
    Map<Long, Person> personMap;

    public PersonDataBase() {
        this.personMap = new HashMap<>();
    }

    public Person findById(Long id) {
        return personMap.get(id);
    }

    public void add(Person person) {
        personMap.put(person.getId(), person);
    }

    public boolean isManager(Person person) {
        return getPerson(person.getId()).getPosition().isManager();
    }

    public boolean isEmployee(Long id) {
        return !isManager(getPerson(id));
    }

    private Person getPerson(Long id) {
        Person person = findById(id);

        if (person == null) {
            throw new IllegalArgumentException("Запись с ID " + id + " отсутствует в БД");
        }

        return person;
    }
}
