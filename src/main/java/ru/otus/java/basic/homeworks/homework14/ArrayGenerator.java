package ru.otus.java.basic.homeworks.homework14;

public class ArrayGenerator {
    public static final int ARRAY_LENGTH = 100_000_000;
    public static final int THREADS_NUMBER = 4;

    public static void main(String[] args) {
        System.out.printf("Старт заполнения массива в %d элементов в один поток.%n", ARRAY_LENGTH);
        Measure.stamp();
        double[] array1 = generateSequential(ARRAY_LENGTH);
        Measure.print();

        System.out.printf("Старт заполнения массива длиной %d в %d потока.%n", ARRAY_LENGTH, THREADS_NUMBER);
        Measure.stamp();
        double[] array2 = generateParallel(ARRAY_LENGTH, THREADS_NUMBER);
        Measure.print();
    }

    public static double[] generateSequential(int length) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            array[i] = calculate(i);
        }
        return array;
    }

    public static double[] generateParallel(int length, int threadsNumber) {
        if (threadsNumber > length) {
            throw new RuntimeException("Слишком большое число потоков");
        }
        double[] array = new double[length];
        Thread[] threads = new Thread[threadsNumber];
        int limit = length / threadsNumber;
        for (int i = 0; i < threadsNumber; i++) {
            int startIndex = i * limit;
            int endIndex = i == threadsNumber - 1 ? length - 1 : startIndex + limit - 1;
            threads[i] = new Thread(() -> {
                System.out.printf("Старт потока: %s. Начальный индекс: %d. Конечный индекс: %d%n", Thread.currentThread().getName(), startIndex, endIndex);
                for (int j = startIndex; j <= endIndex; j++) {
                    array[j] = calculate(j);
                }
                System.out.printf("Завешение потока: %s.%n", Thread.currentThread().getName());
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return array;
    }

    public static double calculate(int i) {
        return 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
    }
}
