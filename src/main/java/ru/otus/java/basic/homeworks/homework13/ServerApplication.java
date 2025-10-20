package ru.otus.java.basic.homeworks.homework13;

import ru.otus.java.basic.homeworks.homework13.Service.Calculator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {
    public static final int PORT = 12345;
    public static final String stopWord = "stop";

    public static void main(String[] args) {
        Application application = createService();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.printf("Приложение %s начало работу%n", application.getName());
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент установил соединение с сервером");
                DataStreamClient client = new DataStreamClient(clientSocket);
                String input = client.read();
                if (stopWord.equalsIgnoreCase(input.trim())) {
                    System.out.println("Текущий клиент закончил свою работу");
                    client.close();
                    continue;
                }
                try {
                    client.send(application.run(input));
                } catch (ApplicationException e) {
                    client.send(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.printf("Приложение %s завершило работу с ошибкой: %s%n", application.getName(), e.getMessage());
        }
    }

    private static Application createService() {
        return new Calculator();
    }
}
