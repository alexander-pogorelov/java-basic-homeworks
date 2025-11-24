package ru.otus.java.basic.homeworks.homework21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Homework21 {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedPrinter printer = new SynchronizedPrinter(new Character[]{'A', 'B', 'C'}, 5);
        ExecutorService executor = Executors.newFixedThreadPool(printer.getSequenceSize());
        for (int i = 0; i < printer.getSequenceSize(); i++) {
            final int index = i;
            executor.execute(() -> {
                printer.print(printer.getCurrentSymbol(index), printer.getRepeats());
            });
        }
        executor.shutdown();
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
        System.out.println();
    }
}
