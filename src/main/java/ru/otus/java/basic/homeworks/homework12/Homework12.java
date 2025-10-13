package ru.otus.java.basic.homeworks.homework12;

import java.util.List;

public class Homework12 {
    public static void main(String[] args) {
        FileLister lister = new FileLister(".", "txt");
        List<String> fileNames = lister.getTextFiles();
        if (fileNames.isEmpty()) {
            System.out.println("Текстовые файлы в корневой директории проекта не найдены");
        }

        System.out.println("Список текстовых файлов в корневой директории проекта:");
        for (String filename : fileNames) {
            System.out.println(filename);
        }

        ConsoleReader reader = new ConsoleReader(fileNames);
        String selectedFilename = reader.getSelectedFilename();
        System.out.printf("Выбран файл: '%s'.%n", selectedFilename);

        FileContentReader contentReader = new FileContentReader(selectedFilename);
        System.out.printf("Содержимое файла '%s':%n", selectedFilename);
        contentReader.toConsole();

        reader.toFile(selectedFilename, "стоп");
        System.out.printf("Содержимое файла '%s':%n", selectedFilename);
        contentReader.toConsole();
    }
}
