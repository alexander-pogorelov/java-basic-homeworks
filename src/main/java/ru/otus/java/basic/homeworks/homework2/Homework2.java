package ru.otus.java.basic.homeworks.homework2;

import java.util.Arrays;

public class Homework2 {
    public static void main(String[] args) {
        repeatPrint("Hello World!", 5);

        int[] array1 = {1, 3, -8, 9, 12, 0, 5};
        sumAndPrintGreaterThan(array1, 5);
        int[] array2 = {1, 3, -8, -9, 4, 0, 5};
        sumAndPrintGreaterThan(array2, 5);

        int[] array3 = new int[10];
        fillArray(array3, 9);
        int[] array4 = {1, 2, 3, 8, 77, 0};
        fillArray(array4, 11);

        int[] array5 = {1, 33, 6, 0, -5, 18, 100};
        addValueToArrayElements(array5, 100);

        int[] array6 = {1, 2, 3};
        printSumOfLargerHalf(array6);
        int[] array7 = {1, 2, 3, 4};
        printSumOfLargerHalf(array7);
        int[] array8 = {10, 2, 3, 4};
        printSumOfLargerHalf(array8);
        int[] array9 = {1, 2, 2, 1};
        printSumOfLargerHalf(array9);

        int[][] array10 = {{1, 2, 3}, {2, 2}, {1, 1, 1, 1, 1}};
        sumArrays(array10);

        int[][] array11 = {{0, 1, 2, 3}, {2, 3}, {1, 1, 1, 1, 1}, {-2, 3, -1}};
        sumArrays(array11);

        int[] array12 = {1, 1, 1, 1, 1, 5};
        hasBalancePoint(array12);
        int[] array13 = {5, 3, 4, -2};
        hasBalancePoint(array13);
        int[] array14 = {7, 2, 2, 2};
        hasBalancePoint(array14);
        int[] array15 = {9, 4};
        hasBalancePoint(array15);

        int[] array16 = {1, 2, 3, 4, 5, 5, 5, 6, 6, 7, 7, 8, 9, 10};
        isSorted(array16, true);
        int[] array17 = {1, 2, 3, 4, 5, 5, 4, 6, 2, 7, 7, 8, 9, 10};
        isSorted(array17, true);
        int[] array18 = {10, 9, 8, 8, 7, 6, 5, 5, 5, 4, 3, 2, 1, 0, -1, -1};
        isSorted(array18, false);
        int[] array19 = {10, 9, 8, 8, 9, 6, 5, 10, 5, 4, 3, 2, 1, 0, -1, -1};
        isSorted(array19, false);

        int[] array20 = {1, 2, 3, 4, 5, 5, 5, 6, 6, 7, 7, 8, 9, 10};
        reverseArray(array20);
        int[] array21 = {10, 9, 8, 8, 7, 6, 5, 5, 5, 4, 3, 2, 1, 0, -1, -1};
        reverseArray(array21);
    }

    public static void repeatPrint(String string, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(string);
        }
        printLine();
    }

    public static void sumAndPrintGreaterThan(int[] intArray, int value) {
        printInputArray(intArray);
        int sum = 0;
        boolean found = false;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] > value) {
                found = true;
                sum += intArray[i];
            }
        }
        if (found) {
            System.out.printf("Сумма элементов массива, больших %d, равна %d%n", value, sum);
        } else {
            System.out.printf("Элементов массива, больших %d, не обнаружено%n", value);
        }
        printLine();
    }

    public static void fillArray(int[] intArray, int value) {
        printInputArray(intArray);
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = value;
        }

        System.out.println("Массив, заполненный цифрой " + value + ": " + Arrays.toString(intArray));
        printLine();
    }

    public static void addValueToArrayElements(int[] intArray, int value) {
        printInputArray(intArray);
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] += value;
        }
        System.out.println("Массив элементов, увеличенных на " + value + ": " + Arrays.toString(intArray));
        printLine();
    }

    public static void printSumOfLargerHalf(int[] intArray) {
        printInputArray(intArray);
        if (intArray.length % 2 != 0) {
            System.out.println("Количество элементов в массиве должно быть четным");
            printLine();
            return;
        }
        int lefSum = 0;
        for (int i = 0; i < intArray.length / 2; i++) {
            lefSum += intArray[i];
        }
        int rightSum = 0;
        for (int i = intArray.length / 2; i < intArray.length; i++) {
            rightSum += intArray[i];
        }
        if (rightSum > lefSum) {
            System.out.println("Сумма элементов правой половины массива больше суммы левой");
        } else if (rightSum < lefSum) {
            System.out.println("Сумма элементов левой половины массива больше суммы правой");
        } else {
            System.out.println("Сумма элементов левой половины массива равна сумме правой");
        }
        printLine();
    }

    public static void sumArrays(int[][] multipleArray) {
        int maxSingleArrayLength = 0;
        System.out.println("Входящие массивы:");

        for (int i = 0; i < multipleArray.length; i++) {
            printInputArray(multipleArray[i]);
            if (multipleArray[i].length > maxSingleArrayLength) {
                maxSingleArrayLength = multipleArray[i].length;
            }
        }

        int[] result = new int[maxSingleArrayLength];

        for (int i = 0; i < multipleArray.length; i++) {
            for (int j = 0; j < multipleArray[i].length; j++) {
                result[j] += multipleArray[i][j];
            }
        }

        System.out.println("Масиив сумм элементов исходных массивов: " + Arrays.toString(result));
        printLine();
    }

    public static void hasBalancePoint(int[] inputArray) {
        printInputArray(inputArray);
        int totalSum = 0;

        for (int i = 0; i < inputArray.length; i++) {
            totalSum += inputArray[i];
        }

        if (totalSum % 2 != 0) {
            System.out.println("Точка баланса в массиве отсутствует");
            printLine();
            return;
        }

        int leftSum = 0;
        int rightSum = totalSum;

        for (int i = 0; i < inputArray.length; i++) {
            leftSum += inputArray[i];
            rightSum -= inputArray[i];

            if (leftSum == rightSum) {
                System.out.printf("После индекса %d сумма элементов левой части массива равна правой%n", i);
                printLine();
                return;
            }
        }

        System.out.println("Массив не содержит точки, относительно которой сумма элементов слева равна сумме элементов справа");
        printLine();
    }

    public static void isSorted(int[] inputArray, boolean ascending) {
        printInputArray(inputArray);
        for (int i = 0; i < inputArray.length - 1; i++) {
            if (ascending && inputArray[i] > inputArray[i + 1]) {
                System.out.println("Массив не отсортирован по возрастанию");
                printLine();
                return;
            }

            if (!ascending && inputArray[i] < inputArray[i + 1]) {
                System.out.println("Массив не отсортирован по убыванию");
                printLine();
                return;
            }
        }

        if (ascending) {
            System.out.println("Массив отсортирован по возрастанию");
        } else {
            System.out.println("Массив отсортирован по убыванию");
        }

        printLine();
    }

    public static void reverseArray(int[] inputArray) {
        printInputArray(inputArray);

        int[] reverse = new int[inputArray.length];

        for (int i = 0; i < inputArray.length ; i++) {
            reverse[i] = inputArray[inputArray.length - i -1];
        }

        System.out.println("Перевернутый массив: " + Arrays.toString(reverse));
        printLine();
    }

    private static void printLine() {
        System.out.println("-----------------------------");
    }

    private static void printInputArray(int[] inputArray) {
        System.out.println("Входящий массив " + Arrays.toString(inputArray));
    }
}
