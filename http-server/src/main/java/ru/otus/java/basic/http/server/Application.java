package ru.otus.java.basic.http.server;

public class Application {
    public static final int PORT = 8189;
    public static final int THREADS_NUMBER = 4;
    public static void main(String[] args) {
        new HttpServer(PORT).start(THREADS_NUMBER);
    }
}
