package ru.otus.java.basic.homeworks.homework10;

import java.util.*;

public class PhoneBook {
    private final Set<Phone> phones;
    private final Map<Person, Set<Phone>> book;

    public PhoneBook() {
        this.phones = new HashSet<>();
        this.book = new HashMap<>();
    }

    public void add(Person person, Phone phone) throws DuplicatePhoneException {
        addPhone(phone);
        Set<Phone> personPhones = getPhones(person);
        personPhones.add(phone);
        book.put(person, personPhones);
    }

    public Set<Phone> find(Person person) {
        return getPhones(person);
    }

    public boolean containsPhoneNumber(Phone phone) {
        return phones.contains(phone);
    }

    private Set<Phone> getPhones(Person person) {
        Set<Phone> personPhones = book.get(person);
        if (personPhones == null) {
            personPhones = new HashSet<>();
        }
        return personPhones;
    }

    private void addPhone(Phone phone) throws DuplicatePhoneException {
        if (phones.contains(phone)) {
            throw new DuplicatePhoneException(phone);
        }
        phones.add(phone);
    }
}
