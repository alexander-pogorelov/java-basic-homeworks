package ru.otus.java.basic.chat.client;

import ru.otus.java.basic.chat.DataStreamClient;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String host;
    private final int port;
    private DataStreamClient dsc;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        try {
            dsc = new DataStreamClient(new Socket(host, port));
            new Thread(() -> {
                try {
                    while (true) {
                        String message = dsc.read();
                        if (message.startsWith("/")) {
                            if (message.equalsIgnoreCase("/exitok")) {
                                break;
                            }
                        } else {
                            System.out.println(message);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    disconnect();
                }
            }).start();
            while (true) {
                String message = scanner.nextLine();
                dsc.write(message);
                if (message.equalsIgnoreCase("/exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        try {
            dsc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
