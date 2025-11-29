package ru.otus.java.basic.http.server;

public class Application {
    public static final int PORT = 8189;
    public static void main(String[] args) {
        new HttpServer(PORT).start();
    }
}
