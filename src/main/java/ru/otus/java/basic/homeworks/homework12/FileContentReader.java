package ru.otus.java.basic.homeworks.homework12;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileContentReader {
    private final String filename;

    public FileContentReader(String filename) {
        this.filename = filename;
    }

    public void toConsole() {
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8)) {
            int symbol = in.read();
            while (symbol != -1) {
                System.out.print((char) symbol);
                symbol = in.read();
            }
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
