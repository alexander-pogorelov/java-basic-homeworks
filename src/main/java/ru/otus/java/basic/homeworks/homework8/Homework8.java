package ru.otus.java.basic.homeworks.homework8;

public class Homework8 {
    private static final int ROW_SIZE = 4;
    private static final int COLUMN_SIZE = 4;

    public static void main(String[] args) {
        for (String[][] array : getArrays()) {
            print2dArray(array);
            try {
                int sum = sumIntValues(array);
                System.out.printf("Сумма элементов массива равна: %s.%n", sum);
            } catch (AppArraySizeException | AppArrayDataException e) {
                System.out.println(e.getMessage());
            }
            printDelimiter();
        }
    }

    private static int sumIntValues(String[][] array) throws AppArraySizeException, AppArrayDataException {
        if (array.length != Homework8.ROW_SIZE) {
            throw new AppArraySizeException();
        }

        int sum = 0;

        for (int i = 0; i < Homework8.ROW_SIZE; i++) {
            if (array[i].length != Homework8.COLUMN_SIZE) {
                throw new AppArraySizeException();
            }
            for (int j = 0; j < Homework8.COLUMN_SIZE; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException(array[i][j], i, j);
                }
            }
        }
        return sum;
    }

    private static String[][][] getArrays() {
        return new String[][][] {
            {{"1", "2"}, {"3", "4"}},
            {{"1", "2"}, {"3", "4"}, {"5", "6"}, {"7", "8"}},
            {{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"}, {"a", "b", "c", "d"}},
            {{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}}
        };
    }

    private static void print2dArray(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%s\t", array[i][j]);
            }
            System.out.println();
        }
    }

    private static void printDelimiter() {
        System.out.println("---------------------------------");
    }
}
