package socex.core.http;

import org.json.JSONObject;

public class JsonResponseError extends HttpResponseError {
    private final JSONObject json;

    public JsonResponseError(int statusCode, JSONObject json) {
        super(statusCode, json.toString(2));
        this.json = json;
    }

    public JSONObject getJSONObject() {
        return json;
    }
}
