package ru.otus.java.basic.homeworks.homework9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Homework9 {
    public static void main(String[] args) {
        System.out.println(createSequence(10, 20));
        printDelimiter();
        System.out.println(createSequence(-10, 10));
        printDelimiter();

        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 10, 0, -2, 6, 5, 7, 4, 8, 5, 13));
        System.out.println("Входной список: " + list1);
        int number1 = 5;
        System.out.println("Сумма эементов списка, больших " + number1 + ", равна " + sumGreaterThan(list1, number1));
        printDelimiter();

        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 10, 0, -2, 6, 5, 7, 4, 8, 5, 13));
        System.out.println("Входной список: " + list2);
        int number2 = 1;
        replaceAllWith(list2, number2);
        System.out.println("Исходящий список: " + list2);
        printDelimiter();

        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 10, 0, -2, 6, 5, 7, 4, 8, 5, 13));
        System.out.println("Входной список: " + list3);
        int number3 = 3;
        incrementAllBy(list3, number3);
        System.out.println("Исходящий список: " + list3);
        printDelimiter();

        List<Employee> employees = getEmployees();
        System.out.println("Список сотрудников: " + employees);
        List<String> names = extractNames(employees);
        System.out.println("Список имн: " + names);
        printDelimiter();

        System.out.println("Список сотрудников: " + employees);
        int minAge = 35;
        List<Employee> filteredEmployees = filterByAge(employees, minAge);
        System.out.println("Список сотрудников, у которых возраст больше или равен " + minAge + " :" + filteredEmployees);
        printDelimiter();

        System.out.println("Список сотрудников: " + employees);
        int averageAge = 33;
        System.out.println("Средний возраст сотрудников больше " + averageAge + ": " + isAverageAgeAbove(employees, averageAge));
        printDelimiter();

        System.out.println("Список сотрудников: " + employees);
        Employee youngestEmployee = findYoungestEmployee(employees);
        System.out.println("Самый молодой сотрудник: " + youngestEmployee);
        printDelimiter();
    }

    private static List<Integer> createSequence(int min, int max) {
        int capacity = max - min + 1;
        if (min > max) {
            throw new IllegalArgumentException(String.format("Min число %d не может быть больше Max числа %d", min, max));
        }
        List<Integer> arrayList = new ArrayList<>(capacity);
        for (int i = min; i <= max ; i++) {
            arrayList.add(i);
        }
        return arrayList;
    }

    private static int sumGreaterThan(List<Integer> list, int value) {
        int sum = 0;
        for (int item : list) {
            if (item > value) {
                sum += item;
            }
        }
        return sum;
    }

    private static void replaceAllWith(List<Integer> list, int value) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, value);
        }
    }

    private static void incrementAllBy(List<Integer> list, int number) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + number);
        }
    }

    private static List<String> extractNames(List<Employee> employees) {
        List<String> names = new ArrayList<>(employees.size());
        for (Employee employee : employees) {
            names.add(employee.getName());
        }
        return names;
    }

    private static List<Employee> filterByAge(List<Employee> employees, int minAge) {
        List<Employee> filteredEmployees = new LinkedList<>();
        for (Employee employee : employees) {
            if (employee.getAge() >= minAge) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    private static boolean isAverageAgeAbove(List<Employee> employees, int averageAge) {
        int count = 0, agesSum = 0;
        for (Employee employee : employees) {
            count++;
            agesSum += employee.getAge();
        }

        if (count == 0) {
            return false;
        }

        return agesSum/count > averageAge;
    }

    private static Employee findYoungestEmployee(List<Employee> employees) {
        if (employees.isEmpty()) {
            return null;
        }
        int minAge = employees.getFirst().getAge();
        int index = 0;
        for (int i = 1; i < employees.size(); i++) {
            if (employees.get(i).getAge() < minAge) {
                minAge = employees.get(i).getAge();
                index = i;
            }
        }
        return employees.get(index);
    }

    private static void printDelimiter() {
        System.out.println("----------------------------------------------------------------------------------------");
    }

    private static List<Employee> getEmployees() {
        return new ArrayList<>(Arrays.asList(
                new Employee("Василий", 33),
                new Employee("Иван", 21),
                new Employee("Петр", 25),
                new Employee("Михаил", 18),
                new Employee("Евгений", 49),
                new Employee("Владимир", 53),
                new Employee("Александр", 29),
                new Employee("Тимофей", 35),
                new Employee("Тимофей", 28),
                new Employee("Андрей", 51)
        ));
    }
}
