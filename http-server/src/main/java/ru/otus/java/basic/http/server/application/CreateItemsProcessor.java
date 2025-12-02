package ru.otus.java.basic.http.server.application;

import com.google.gson.Gson;
import ru.otus.java.basic.http.server.HttpRequest;
import ru.otus.java.basic.http.server.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CreateItemsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        Item item = gson.fromJson(request.getBody(), Item.class);
        ItemsStorage.createItem(item);
        String response = """
                HTTP/1.1 201 Created\r
                Content-Type: application/json\r
                \r
                """ + gson.toJson(item);
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
