package ru.otus.java.basic.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final int port;
    private final Dispatcher dispatcher;

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
    }

    public void start(int threadNumber) {
        ExecutorService executor = Executors.newFixedThreadPool(threadNumber);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    executor.execute(() -> {
                        try {
                            byte[] buffer = new byte[8192];
                            int n = socket.getInputStream().read(buffer);
                            System.out.println("Запрос начал обрабатывать поток " + Thread.currentThread().getName());
                            if (n < 1) {
                                return;
                            }
                            String rawRequest = new String(buffer, 0, n);
                            HttpRequest request = new HttpRequest(rawRequest);
                            dispatcher.execute(request, socket.getOutputStream());
                            System.out.println("Запрос обработал поток " + Thread.currentThread().getName());
                        } catch (IOException e) {
                            System.err.println("Ошибка при обработке запроса: " + e.getMessage());
                        } finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                //
                            }
                        }
                    });
                } catch (IOException e) {
                    System.err.println("Ошибка при принятии соединения: " + e.getMessage());
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Не удалось запустить сервер на порту " + port + ": " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
