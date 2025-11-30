package ru.otus.java.basic.http.server.application;

import ru.otus.java.basic.http.server.HttpRequest;
import ru.otus.java.basic.http.server.exceptions.BadRequestException;
import ru.otus.java.basic.http.server.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DeleteItemsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        int id;
        if (!request.containsParameter("id")) {
            throw new BadRequestException("В запросе отсутствует обязательный параметр запроса 'id'");
        }
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Параметр запроса 'id' имеет некорректный формат");
        }
        ItemsStorage.deleteItem(id);
        String  response = """
                HTTP/1.1 204 No Content\r
                \r
                """;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
