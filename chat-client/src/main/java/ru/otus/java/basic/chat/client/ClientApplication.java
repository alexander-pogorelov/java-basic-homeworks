package ru.otus.java.basic.chat.client;

public class ClientApplication {
    public static final String HOST = "localhost";
    public static final int PORT = 12345;

    public static void main(String[] args) {
        new Client(HOST, PORT).start();
    }
}
