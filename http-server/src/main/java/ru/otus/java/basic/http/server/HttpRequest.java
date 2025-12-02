package ru.otus.java.basic.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private static final Logger logger = LogManager.getLogger(HttpRequest.class.getName());
    private final String rawRequest;
    private HttpMethod method;
    private String uri;
    private String body;
    private final Map<String, String> params;

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        params = new HashMap<>();
        parse();
    }

    public String getUri() {
        return uri;
    }

    public String getParameter(String name) {
        return params.get(name);
    }

    public String getBody() {
        return body;
    }

    public boolean containsParameter(String name) {
        return params.containsKey(name);
    }

    public String getRoutingKey() {
        return method + " " + uri;
    }

    private void parse() {
        int left = rawRequest.indexOf(" ");
        int right = rawRequest.indexOf(" ", left + 1);
        method = HttpMethod.valueOf(rawRequest.substring(0, left));
        uri = rawRequest.substring(left + 1, right);
        if (uri.contains("?")) {
            String[] tokens = uri.split("[?]");
            uri = tokens[0];
            String[] keysAndValues = tokens[1].split("[&]");
            for (String o : keysAndValues) {
                String[] keyValue = o.split("[=]");
                params.put(keyValue[0], keyValue[1]);
            }
        }
        if (method == HttpMethod.POST) {
            body = rawRequest.substring(rawRequest.indexOf("\r\n\r\n") + 4);
        }
    }

    public void info(boolean showRawRequest) {
        logger.info("METHOD: {}", method);
        logger.info("URI: {}", uri);
        logger.info("PARAMS: {}", params);
        logger.info("BODY: {}", body);
        logger.debug(rawRequest);
    }
}
