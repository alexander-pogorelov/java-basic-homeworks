package ru.otus.java.basic.chat.server;

public class ServerApplication {
    public static final int PORT = 12345;

    public static void main(String[] args) {
        new Server(PORT).start();
    }
}
