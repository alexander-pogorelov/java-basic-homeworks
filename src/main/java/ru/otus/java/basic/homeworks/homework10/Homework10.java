package ru.otus.java.basic.homeworks.homework10;

public class Homework10 {
    public static void main(String[] args) {
        Person person1 = new Person("Иванов", "Иван");
        Person person2 = new Person("Иванов", "Евгений");
        Person person4 = new Person("Сидоов", "Николай");
        Person person5 = new Person("Петров", "Геннадий");
        Person person6 = new Person("Эльдаров", "Святослав");
        Person person7 = new Person("Поляов", "Михаил");
        Person person8 = new Person("Иванов", "Иван");
        Person person9 = new Person("Петров", "Геннадий");

        Phone phone1 = new Phone(375_29_111_11_11L);
        Phone phone1_2 = new Phone(375_29_111_11_99L);
        Phone phone2 = new Phone(375_29_222_22_22L);
        Phone phone2_2 = new Phone(375_29_222_22_99L);
        Phone phone2_3 = new Phone(375_29_555_55_55L);
        Phone phone8 = new Phone(375_29_888_88_88L);
        Phone phone4 = new Phone(375_29_444_44_44L);
        Phone phone6 = new Phone(375_29_666_66_66L);
        Phone phone7 = new Phone(375_29_777_77_77L);
        Phone phone3 = new Phone(375_29_333_33_33L);

        try {
            PhoneBook book = new PhoneBook();
            book.add(person1, phone1);
//            book.add(person2, new Phone(375_29_111_11_11L));
            book.add(person2, phone2);

            printPersonPhoneNumbers(book, person5);
            printPersonPhoneNumbers(book, person2);

            book.add(person8, phone8);
            printPersonPhoneNumbers(book, person8);

            book.add(person2, phone2_2);
            printPersonPhoneNumbers(book, person2);

            book.add(person1, phone1_2);
            printPersonPhoneNumbers(book, person1);
            printPersonPhoneNumbers(book, person8);

            printPhoneBookHasPhone(book, phone4);
            book.add(person4, phone4);
            printPhoneBookHasPhone(book, phone4);

            book.add(person6, phone6);
            book.add(person6, phone7);
            printPhoneBookHasPhone(book, phone6);
            printPhoneBookHasPhone(book, phone7);
            printPersonPhoneNumbers(book, person6);

            book.add(person1, phone3);
            printPersonPhoneNumbers(book, person1);
            printPersonPhoneNumbers(book, person8);

            book.add(person2, phone2_3);
            printPersonPhoneNumbers(book, person2);

        } catch (DuplicatePhoneException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printPhoneBookHasPhone(PhoneBook book, Phone phone) {
        System.out.println("Телефон " + phone + " есть в телефонной книге: " + book.containsPhoneNumber(phone));
    }

    private static void printPersonPhoneNumbers(PhoneBook book, Person person) {
        System.out.println("Номеа телефонов " + person +" : " + book.find(person));
    }
}
