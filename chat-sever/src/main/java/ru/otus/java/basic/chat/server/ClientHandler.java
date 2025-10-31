package ru.otus.java.basic.chat.server;

import ru.otus.java.basic.chat.DataStreamClient;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class ClientHandler {
    private final Server server;
    private final DataStreamClient dsc;
    private final int clientPort;
    private String username;
    private boolean authenticated;

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.dsc = new DataStreamClient(socket);
        this.clientPort = socket.getPort();
    }

    public void handle() {
        new Thread(() -> {
            System.out.printf("Клиент подключился к чату на порту %s%n", clientPort);
            try {
                // Цикл аутентификации
                while (true) {
                    sendMessage("Перед работой с чатом необходимо выполнить аутентификацию '/auth login password'"
                    + " или регистрацию '/reg login password username'");
                    String message = dsc.read();
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exit")) {
                            sendMessage("/exitok");
                            break;
                        }
                        // /auth login password
                        if (message.startsWith("/auth ")) {
                            String[] parts = message.trim().split("\\s+");
                            if (parts.length != 3) {
                                sendMessage("Неверный формат команды /auth");
                                continue;
                            }
                            if (server.getAuthProvider().authenticate(this, parts[1], parts[2])) {
                                server.subscribe(this);
                                authenticated = true;
                                sendMessage("Вы подключились с ником: " + username);
                                break;
                            }
                            continue;
                        }
                        // /reg login password username
                        if (message.startsWith("/reg ")) {
                            String[] parts = message.trim().split("\\s+");
                            if (parts.length != 4) {
                                sendMessage("Неверный формат команды /reg");
                                continue;
                            }
                            if (server.getAuthProvider().register(this, parts[1], parts[2], parts[3])) {
                                server.subscribe(this);
                                authenticated = true;
                                sendMessage("Вы подключились с ником: " + username);
                                break;
                            }
                            continue;
                        }
                    }
                }
                // Цикл работы
                while (authenticated) {
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
