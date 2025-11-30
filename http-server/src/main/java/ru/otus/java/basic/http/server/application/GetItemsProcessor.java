package ru.otus.java.basic.http.server.application;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.http.server.HttpRequest;
import ru.otus.java.basic.http.server.exceptions.BadRequestException;
import ru.otus.java.basic.http.server.processors.DefaultNotFoundProcessor;
import ru.otus.java.basic.http.server.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetItemsProcessor implements RequestProcessor {
    private static final Logger logger = LogManager.getLogger(GetItemsProcessor.class.getName());
    private final RequestProcessor defaultNotFoundProcessor = new DefaultNotFoundProcessor();

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        if (request.containsParameter("id")) {
            logger.debug("request contains id parameter");
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                logger.debug("request contains id " + id);
                Item item = ItemsStorage.getItem(id);
                if (item == null) {
                    defaultNotFoundProcessor.execute(request, output);
                } else {
                    String itemJson = gson.toJson(item);
                    sendJsonResponse(output, itemJson);
                }
                return;
            } catch (NumberFormatException e) {
                logger.debug("request contains id not a number");
                throw new BadRequestException("Параметр запроса 'id' имеет некорректный формат");
            }
        }
        logger.debug("request does not contain id parameter");
        String itemsJson = gson.toJson(ItemsStorage.getItems());
        sendJsonResponse(output, itemsJson);
    }

    private void sendJsonResponse(OutputStream output, String json) throws IOException {
        String response = """
                HTTP/1.1 200 OK\r
                Content-Type: application/json\r
                \r
                """ + json;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
