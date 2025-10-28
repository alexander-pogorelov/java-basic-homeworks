package ru.otus.java.basic.chat.server;

import ru.otus.java.basic.chat.DataStreamClient;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class ClientHandler {
    private final Server server;
    private final DataStreamClient dsc;
    private String username;

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.dsc = new DataStreamClient(socket);
        this.username = "user" + socket.getPort();
    }

    public void handle() {
        new Thread(() -> {
            System.out.printf("Клиент %s подключился к чату%n", username);
            sendMessage("Вы подключились с ником " + username);
            try {
                while (true) {
                    String message = dsc.read();
                    if (message.startsWith("/")) {
                        if (message.equalsIgnoreCase("/exit")) {
                            sendMessage("/exitok");
                            break;
                        }
                        if (message.startsWith("/w ")) {
                            String[] parts = message.trim().split("\\s+", 3);
                            server.sendPrivateMessage(this, parts[1], parts[2]);
                            continue;
                        }
                        server.handleUnknownCommand(this);
                    } else {
                        server.sendBroadcastMessage(username + ": " + message);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMessage(String message) {
        try {
            dsc.write(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void disconnect() {
        server.unsubscribe(this);
        try {
            dsc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClientHandler that = (ClientHandler) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
