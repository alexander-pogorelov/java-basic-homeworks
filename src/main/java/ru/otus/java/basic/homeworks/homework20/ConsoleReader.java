package ru.otus.java.basic.homeworks.homework20;

import java.util.Scanner;
import java.util.Set;

public class ConsoleReader {
    private final Set<String> filenames;
    private final Scanner scanner;

    public ConsoleReader(Set<String> filenames) {
        this.filenames = filenames;
        this.scanner = new Scanner(System.in);
    }

    public String getSelectedFilename() {
        System.out.println("Выберете полное имя файла, с которым хотите работать:");
        String filename;
        while (true) {
            try {
                filename = scanner.nextLine();
                if (hasFilename(filename)) {
                    return filename;
                }
                System.out.printf("Файл %s не найден. Повторите ввод:%n", filename);
            } catch (Throwable e) {
                System.out.printf("Введены некорректные данные. %s%n", e.getMessage() != null ? e.getMessage() : "");
            }
        }
    }

    public String getSelectedSequence() {
        System.out.println("Введите последовательность символов для поиска (не менее 2-х):");
        String sequence;
        while (true) {
            try {
                sequence = scanner.nextLine();
                if (sequence.length() >= 2) {
                    return sequence;
                }
                System.out.println("Некорректная последовательность символов.");
            } catch (Throwable e) {
                System.out.printf("Введены некорректные данные. %s%n", e.getMessage() != null ? e.getMessage() : "");
            }
        }
    }

    private boolean hasFilename(String filename) {
        return filenames.contains(filename);
    }
}
