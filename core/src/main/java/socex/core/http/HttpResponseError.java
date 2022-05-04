package socex.core.http;

public class HttpResponseError extends Exception {
    private final int statusCode;
    public HttpResponseError(int statusCode, String message) {
        super("Http response code " + statusCode + ": " + message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
