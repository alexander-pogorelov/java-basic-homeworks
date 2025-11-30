package ru.otus.java.basic.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.http.server.application.ItemsStorage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private static final Logger logger = LogManager.getLogger(HttpServer.class.getName());
    private final int port;
    private final Dispatcher dispatcher;

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        ItemsStorage.init();
    }

    public void start(int threadNumber) {
        ExecutorService executor = Executors.newFixedThreadPool(threadNumber);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен на порту: " + port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    executor.execute(() -> {
                        try {
                            byte[] buffer = new byte[8192];
                            int n = socket.getInputStream().read(buffer);
                            logger.info("Запрос начал обрабатывать поток " + Thread.currentThread().getName());
                            if (n < 1) {
                                return;
                            }
                            String rawRequest = new String(buffer, 0, n);
                            HttpRequest request = new HttpRequest(rawRequest);
                            dispatcher.execute(request, socket.getOutputStream());
                            logger.info("Запрос обработал поток " + Thread.currentThread().getName());
                        } catch (IOException e) {
                            logger.error("Ошибка при обработке запроса: ", e);
                        } finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                //
                            }
                        }
                    });
                } catch (IOException e) {
                    logger.error("Ошибка при принятии соединения: ", e);
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("Не удалось запустить сервер на порту " + port, e);
        } finally {
            executor.shutdown();
        }
    }
}
