package ru.otus.java.basic.homeworks.homework3;

public class Homework3 {
    public static void main(String[] args) {
        int[][] array1 = {{0, 1, 2, 3}, {2, 3}, {1, 1, 1, 1, 1}, {-2, 3, -1}};
        int sum1 = sumElementsGraterThanValue(array1, 0);
        int sum2 = sumElementsGraterThanValue(array1, 2);

        printSquare(4, '*');
        printSquare(10, '$');

        fillDiagonal(array1, 0);
        int[][] array2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        fillDiagonal(array2, 0);
        fillDiagonal(array2, 100);
        int[][] array3 = {{10, 10, 10, 10, 10}, {10, 10, 10, 10, 10}, {10, 10, 10, 10, 10}, {10, 10, 10, 10, 10}, {10, 10, 10, 10, 10}};
        fillDiagonal(array3, 0);

        int[][] array4 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int max1 = findMax(array1);
        int max2 = findMax(array2);
        int max3 = findMax(array3);
        int max4 = findMax(array4);

        int[][] array5 = new int[3][];
        array5[0] = new int[]{1, 2, 3, 4};
        array5[1] = null;
        array5[2] = new int[5];
        int sum3 = sumSecondRow(array5);
        int sum4 = sumSecondRow(array4);
    }

    public static int sumElementsGraterThanValue(int[][] array, int value) {
        printInt2dArray(array);
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > value) {
                    sum += array[i][j];
                }
            }
        }

        System.out.printf("Сумма элементов массива, больших %d, равна %d.%n", value, sum);
        printLine();

        return sum;
    }

    public static void printSquare(int size, char symbol) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j > 0 && j < size - 1 && i > 0 && i < size - 1) {
                    System.out.print(" ");
                    continue;
                }
                System.out.print(symbol);
            }
            System.out.println();
        }
        printLine();
    }

    public static void fillDiagonal(int[][] array, int value) {
        printInt2dArray(array);
        if (!isArraySquare(array)) {
            System.out.println("Двумерный массив не является квадратом");
            printLine();
            return;
        }
        System.out.println();
        int size = array.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i + j == size - 1) {
                    array[i][j] = value;
                }
            }
        }
        printInt2dArray(array);
        printLine();
    }

    public static int findMax(int[][] array) {
        printInt2dArray(array);
        int max = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
        System.out.println("Максимальное число в массиве: " + max);
        printLine();
        return max;
    }

    public static int sumSecondRow(int[][] array) {
        printInt2dArray(array);
        if (array[1] == null) {
            System.out.println("Сумма элементов массива второй строки равна: -1");
            printLine();
            return -1;
        }
        int sum = 0;
        for (int j = 0; j < array[1].length; j++) {
            sum += array[1][j];
        }
        System.out.println("Сумма элементов массива второй строки равна: " + sum);
        printLine();
        return sum;
    }

    private static boolean isArraySquare(int[][] array) {
        int size = array.length;
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != size) {
                return false;
            }
        }
        return true;
    }

    private static void printInt2dArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                System.out.println("Строка в массиве не апределена");
                continue;
            }
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%d\t", array[i][j]);
            }
            System.out.println();
        }
    }

    private static void printLine() {
        System.out.println("-----------------------------");
    }
}
