package ru.otus.java.basic.homeworks.homework20;

import java.util.Set;

public class Homework20 {
    public static void main(String[] args) {
        FileLister lister = new FileLister(".", "txt");
        Set<String> fileNames = lister.getTextFiles();
        if (fileNames.isEmpty()) {
            System.out.println("Текстовые файлы в корневой директории проекта не найдены");
            return;
        }

        System.out.println("Список текстовых файлов в корневой директории проекта:");
        for (String filename : fileNames) {
            System.out.println(filename);
        }

        ConsoleReader console = new ConsoleReader(fileNames);
        String selectedFilename = console.getSelectedFilename();
        System.out.printf("Выбран файл: '%s'.%n", selectedFilename);

        FileContentReader contentReader = new FileContentReader(selectedFilename);
        System.out.printf("Содержимое файла '%s':%n", selectedFilename);
        contentReader.toConsole();

        String sequence = console.getSelectedSequence();
        System.out.printf("Выбрана последовательность: '%s'.%n", sequence);

        SequenceSearcher searcher = new SequenceSearcher(selectedFilename);
        int count = searcher.countSequenceInFile(sequence);
        System.out.printf("Количество вхождений последовательности символов %s в файле %s равно %d%n", sequence, selectedFilename, count);
    }
}
