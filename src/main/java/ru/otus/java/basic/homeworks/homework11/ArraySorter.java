package ru.otus.java.basic.homeworks.homework11;

public class ArraySorter {
    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int length = array.length;
        while (true) {
            boolean isSorted = true;
            for (int i = 0; i < length -1; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
            length--;
        }
    }

    public static void quickSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickSortRecursive(array, 0, array.length - 1);
    }

    private static void quickSortRecursive(int[] array, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }
        int pivotIndex = partition(array, lowIndex, highIndex);
        quickSortRecursive(array, lowIndex, pivotIndex - 1);
        quickSortRecursive(array, pivotIndex + 1, highIndex);
    }

    private static int partition(int[] array, int lowIndex, int highIndex) {
        int pivot = array[lowIndex];
        int leftIndex = lowIndex + 1;
        int rightIndex = highIndex;
        while (true) {
            while (leftIndex <= rightIndex && array[leftIndex] <= pivot) {
                leftIndex++;
            }
            while (rightIndex >= leftIndex && array[rightIndex] >= pivot) {
                rightIndex--;
            }
            if (rightIndex < leftIndex) {
                break;
            } else {
                swap(array, leftIndex, rightIndex);
            }
        }
        swap(array, lowIndex, rightIndex);
        return rightIndex;
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
