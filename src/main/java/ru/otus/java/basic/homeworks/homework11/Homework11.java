package ru.otus.java.basic.homeworks.homework11;

import java.util.Arrays;

public class Homework11 {
    public static void main(String[] args) {
        PersonDataBase dataBase = new PersonDataBase();
        Person person1 = new Person("Person1", Position.DIRECTOR, 123L);
        dataBase.add(person1);
        Person person2 = new Person("Person2", Position.QA, 18697L);
        dataBase.add(person2);

        Person person3 = new Person("Person3", Position.DEVELOPER, 112233L);

        printIsManager(dataBase, person1);
        printIsManager(dataBase, person2);
        try {
            printIsManager(dataBase, person3);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        printIsEmployee(dataBase, person1.getId());
        printIsEmployee(dataBase, person2.getId());
        try {
            printIsEmployee(dataBase, person3.getId());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        printFindById(dataBase, person1.getId());
        printFindById(dataBase, person2.getId());
        printFindById(dataBase, person3.getId());
        printFindById(dataBase, 999998888888L);


        int[] array1 = {-1, 33, 6, 9, 0, 5, 21, -8, 5, 2, 0, -9, 3, 7, -10};
        int[] array1_2 = {-1, 33, 6, 9, 0, 5, 21, -8, 5, 2, 0, -9, 3, 7, -10};
        printArray(array1);
        ArraySorter.bubbleSort(array1);
        printArray(array1);
        ArraySorter.quickSort(array1_2);
        printArray(array1_2);
        printDelimiter();

        int[] array2 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] array2_1 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        printArray(array2);
        ArraySorter.bubbleSort(array2);
        printArray(array2);
        ArraySorter.quickSort(array2_1);
        printArray(array2_1);
        printDelimiter();

        int[] array3 = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] array3_1 = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        printArray(array3);
        ArraySorter.bubbleSort(array3);
        printArray(array3);
        ArraySorter.quickSort(array3_1);
        printArray(array3_1);
        printDelimiter();

        int[] array4 = {1, 2, 3, 3, 5, 5, 6, 7, 7};
        int[] array4_1 = {1, 2, 3, 3, 5, 5, 6, 7, 7};
        printArray(array4);
        ArraySorter.bubbleSort(array4);
        printArray(array4);
        ArraySorter.quickSort(array4_1);
        printArray(array4_1);
        printDelimiter();

        int[] array5 = {3, 3, 2, 2, 1, 1, 0, 0, 1, 1};
        int[] array5_1 = {3, 3, 2, 2, 1, 1, 0, 0, 1, 1};
        printArray(array5);
        ArraySorter.bubbleSort(array5);
        printArray(array5);
        ArraySorter.quickSort(array5_1);
        printArray(array5_1);
        printDelimiter();
    }

    private static void printIsManager(PersonDataBase dataBase, Person person) {
        System.out.println(person + " является манеджером: " + dataBase.isManager(person));
    }

    private static void printIsEmployee(PersonDataBase dataBase, Long id) {
        System.out.println("Персона с ID " + id + " является сотрудником: " + dataBase.isEmployee(id));
    }

    private static void printFindById(PersonDataBase dataBase, Long id) {
        System.out.println("Результат поиска в БД по ID " + id + ": " + dataBase.findById(id));
    }

    private static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }
    private static void printDelimiter() {
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
