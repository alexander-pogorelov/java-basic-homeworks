package ru.otus.java.basic.homeworks.homework12;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileLister {
    private final String pathname;
    private final String fileExtension;

    public FileLister(String pathname, String fileExtension) {
        this.pathname = pathname;
        this.fileExtension = fileExtension.toLowerCase();
    }

    public List<String> getTextFiles() {
        List<String> list = new ArrayList<>();
        File projectRoot = new File(pathname);
        File[] files = projectRoot.listFiles();
        if (files == null) {
            System.out.println("В корневой папке проекта нет файлов или вложенных папок");
            return list;
        }
        for (File file : files) {
            if (!file.isFile()) {
                continue;
            }
            if (!fileExtension.equals(getFileExtension(file))) {
                continue;
            }
            list.add(file.getName());
        }
        return list;
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
