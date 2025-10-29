package ru.otus.java.basic.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private final int port;
    private final Map<String,ClientHandler> clients;

    public Server(int port) {
        this.port = port;
        this.clients = new ConcurrentHashMap<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер начал работу на порту " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(this, socket);
                subscribe(clientHandler);
                clientHandler.handle();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void unsubscribe(ClientHandler clientHandler) {
        sendBroadcastMessage(String.format("Клиент %s вышел из чата", clientHandler.getUsername()));
        System.out.printf("Клиент %s вышел из чата%n", clientHandler.getUsername());
        clients.remove(clientHandler.getUsername());
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.put(clientHandler.getUsername(), clientHandler);
    }

    public void sendBroadcastMessage(String message) {
        for (ClientHandler clientHandler : clients.values()) {
            clientHandler.sendMessage(message);
        }
    }

    public void sendPrivateMessage(ClientHandler fromClient, String toUsername, String message) {
        ClientHandler toClient = clients.get(toUsername);
        if (toClient == null) {
            fromClient.sendMessage(String.format("server: пользователь с ником %s не найден", toUsername));
            return;
        }
        if (fromClient.equals(toClient)) {
            fromClient.sendMessage("server: Вы отправили сообщение самому себе");
            return;
        }
        toClient.sendMessage(fromClient.getUsername() + ": " + message);
        fromClient.sendMessage(fromClient.getUsername() + ": " + message);
    }

    public void handleUnknownCommand(ClientHandler fromClient) {
        fromClient.sendMessage("server: неизвестная команда");
    }
}
