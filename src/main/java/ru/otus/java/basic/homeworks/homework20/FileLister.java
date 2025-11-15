package ru.otus.java.basic.homeworks.homework20;

import java.io.File;
import java.util.*;

public class FileLister {
    private final String pathname;
    private final String fileExtension;
    private final Set<String> filenames;

    public FileLister(String pathname, String fileExtension) {
        this.pathname = pathname;
        this.fileExtension = fileExtension.toLowerCase();
        this.filenames = new HashSet<>();
    }

    public Set<String> getTextFiles() {
        File projectRoot = new File(pathname);
        File[] files = projectRoot.listFiles();
        if (files == null) {
            return filenames;
        }
        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }
            if (!fileExtension.equals(getFileExtension(file))) {
                continue;
            }
            filenames.add(file.getName());
        }
        return filenames;
    }

    public String getFileExtension(File file) {
        String name = file.getName();
        int dotPosition = name.lastIndexOf('.');
        if (dotPosition > 0 && dotPosition < name.length() - 1) {
            return name.substring(dotPosition + 1).toLowerCase();
        }
        return "";
    }
}
