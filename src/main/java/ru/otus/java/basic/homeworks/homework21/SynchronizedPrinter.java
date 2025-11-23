package ru.otus.java.basic.homeworks.homework21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SynchronizedPrinter {
    private final int repeats;
    private final List<Character> sequence;
    private Character currentSymbol;

    public SynchronizedPrinter(Character[] symbols, int repeats) {
        if (symbols.length < 2) {
            throw new IllegalArgumentException("Необходимо передать не менее 2-х символов.");
        }
        this.sequence = new ArrayList<>(Arrays.asList(symbols));
        this.currentSymbol = symbols[0];
        this.repeats = repeats;
    }

    public void print(Character symbol, int repeats) {
        for (int i = 0; i < repeats; i++) {
            try {
                Thread.sleep(10 + (int) (Math.random() * 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (this) {
                while (currentSymbol != symbol) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(symbol);
                int currentIndex = sequence.indexOf(symbol);
                currentSymbol = sequence.get(getNextIndex(currentIndex));
                this.notifyAll();
            }
        }
    }

    public int getSequenceSize() {
        return sequence.size();
    }

    public int getRepeats() {
        return repeats;
    }

    public Character getCurrentSymbol(int index) {
        return sequence.get(index);
    }

    private int getNextIndex(int currentIndex) {
        return (currentIndex + 1) % sequence.size();
    }
}
