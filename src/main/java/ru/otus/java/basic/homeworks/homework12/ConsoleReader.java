package ru.otus.java.basic.homeworks.homework12;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader {
    private final List<String> fileList;
    private final Scanner scanner;

    public ConsoleReader(List<String> fileList) {
        this.fileList = fileList;
        this.scanner = new Scanner(System.in);
    }

    public String getSelectedFilename() {
        System.out.println("Выберете полное имя файла, с которым хотите работать:");
        String filename;
        while (true) {
            try {
                filename = scanner.nextLine();
                if (fileListHasFilename(filename)) {
                    return filename;
                }
                System.out.printf("Файл %s не найден. Повторите ввод:%n", filename);
            } catch (Throwable e) {
                System.out.printf("Введены некорректные данные. %s%n", e.getMessage() != null ? e.getMessage() : "");
            }
        }
    }

    public void toFile(String filename, String stopWord) {
        if (!fileListHasFilename(filename)) {
            System.out.printf("Файл '%s' не найден.%n", filename);
            return;
        }
        System.out.printf("Введите текст (введите '%s' для завершения):%n",stopWord);
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8)) {
            while (true) {
                String line = scanner.nextLine();
                if (stopWord.equalsIgnoreCase(line.trim())) {
                    break;
                }
                writer.write(line);
                writer.write(System.lineSeparator());
            }
            System.out.printf("Текст сохранён в %s.%n", filename);
        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
    }

    private boolean fileListHasFilename(String filename) {
        return fileList.contains(filename);
    }
}
