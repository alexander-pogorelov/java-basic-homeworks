package ru.otus.java.basic.homeworks.homework13;

import ru.otus.java.basic.homeworks.homework13.Service.Calculator;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientApplication {
    public static final String HOST = "localhost";
    public static final int PORT = 12345;
    public static final String stopWord = "stop";

    public static void main(String[] args) {
        Application application = createService();
        System.out.println("Приложение: " + application.getName());
        System.out.println("Стоп-слово: " + stopWord);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try (Socket socket = new Socket(HOST, PORT)) {
                DataStreamClient client = new DataStreamClient(socket);
                System.out.println(application.getPrompt());
                String input = scanner.nextLine();
                if (stopWord.equalsIgnoreCase(input.trim())) {
                    client.send(input.trim());
                    System.out.printf("Получено стоп-слово. Приложение %s завершило свою работу%n", application.getName());
                    break;
                }
                client.send(input);
                String response = client.read();
                System.out.printf("Приложение %s ответило: %s%n", application.getName(), response);
            } catch (IOException e) {
                System.out.printf("Приложение %s завершило работу с ошибкой: %s%n", application.getName(), e.getMessage());
            }
        }
    }

    private static Application createService() {
        return new Calculator();
    }
}
