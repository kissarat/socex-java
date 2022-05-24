package socex.core.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

public class RestClient {
    private final List<String> methodsWithBody = Arrays.asList("POST", "PUT", "PATCH");
    private final List<String> methodsWithoutBody = Arrays.asList("GET", "DELETE");

    public HttpURLConnection request(RequestOptions request) throws IOException {
        String method = request.getMethod();
        var connection = (HttpURLConnection) request.getURL().openConnection();
        connection.setRequestMethod(method);
        if (methodsWithBody.contains(method)) {
            connection.setDoOutput(true);
            connection.setRequestProperty("content-length", "0");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
        } else if (!methodsWithoutBody.contains(method)) {
            throw new Error("Unknown method HTTP " + method);
        }
        int timeout = request. getTimeout();
        if (timeout > 0) {
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
        }
        request.getHeaders().forEach(connection::addRequestProperty);
        return connection;
    }
}
