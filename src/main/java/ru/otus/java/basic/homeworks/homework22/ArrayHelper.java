package ru.otus.java.basic.homeworks.homework22;

public class ArrayHelper {
    public ArrayHelper() {
    }

    public int[] sliceAfterLast(int[] array, int number) {
        if (array == null) {
            throw new RuntimeException("Входной массив не может быть null");
        }
        int lastIndex = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == number) {
                lastIndex = i;
                break;
            }
        }
        if (lastIndex == -1) {
            throw new RuntimeException("Входной массив не содержит " + number);
        }
        int[] result = new int[array.length - lastIndex - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = array[lastIndex + 1 + i];
        }
        return result;
    }

    public boolean hasOnly(int[] array, int numberOne,  int numberTwo) {
        if (numberOne == numberTwo) {
            throw new RuntimeException("Числа для поиска должны различаться");
        }
        if (array == null || array.length == 0) {
            return false;
        }
        boolean hasOne = false;
        boolean hasTwo = false;
        for (int value : array) {
            if (value == numberOne) {
                hasOne = true;
            }  else if (value == numberTwo) {
                hasTwo = true;
            } else {
                return false;
            }
        }
        return hasOne && hasTwo;
    }
}
