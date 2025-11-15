package ru.otus.java.basic.homeworks.homework20;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SequenceSearcher {
    private final String fileName;

    public SequenceSearcher(String fileName) {
        this.fileName = fileName;
    }

    public int countSequenceInFile(String sequence) {
        if (sequence.isEmpty()) {
            return 0;
        }
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                count += countSequenceInString(line, sequence);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    private static int countSequenceInString(String content, String sequence) {
        if (content.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = content.indexOf(sequence, index)) != -1) {
            count++;
            index += 1;
        }
        return count;
    }
}
