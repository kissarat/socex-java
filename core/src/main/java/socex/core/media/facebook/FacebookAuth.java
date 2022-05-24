package socex.core.media.facebook;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import socex.core.Store;
import socex.core.http.HttpResponseError;
import socex.core.http.RequestOptions;
import socex.core.http.StringDictionary;
import socex.core.media.HttpPoster;

import java.io.IOException;
import java.util.Date;

public class FacebookAuth extends HttpPoster  {
    final static Logger logger = LoggerFactory.getLogger(FacebookAuth.class);
    public static final String API_VERSION = "13.0";
    public static final String GRAPH_ORIGIN = "https://graph.facebook.com/v" + API_VERSION;
    public static final String USER_ORIGIN = "https://www.facebook.com/v" + API_VERSION;

    public FacebookAuth(Store store) {
        super(store);
    }


    public String createURL(String path, StringDictionary query) {
        return GRAPH_ORIGIN + path + "?" + query;
    }

    public StringDictionary createAuthQuery() {
        return new StringDictionary()
                .set("client_id", store.get("app.id"))
                .set("client_secret", store.get("app.secret"));
    }

    public String createLoginDialogURL(String state) {
        logger.info("Create login dialog URL for " + state);
        return USER_ORIGIN + "/dialog/oauth?" +
                new StringDictionary()
                        .set("client_id", store.get("app.id"))
                        .set("state", state)
                        .set("redirect_uri", store.get("redirect"));
    }

    public String createClientCodeURL(String machineId, String code) {
        return createURL(
                "/oauth/access_token",
                createAuthQuery()
                        .set("machine_id", machineId)
                    .set("code", code)
                    .set("redirect_uri", store.get("redirect"))
        );
    }

    public void setAccessToken(String token, int expiredIn) {
        var expiredAt = new Date(System.currentTimeMillis() + (expiredIn * 1000L));
        logger.info("Set access token token that will expired at " + expiredAt.toString());
        store.set("token", token, expiredIn);
    }

    public String getGrantAccessTokenURL() {
        return String.format("%s/oauth/access_token?grant_type=fb_exchange_token&client_id=%s&client_secret=%s&fb_exchange_token=SHORT-LIVED-USER-ACCESS-TOKEN", store.get("id"), store.get("secret"));
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && !store.isEmpty("app.id");
    }

    protected JSONObject authenticatedRequest(RequestOptions options) throws IOException, HttpResponseError {
        options
                .getQuery()
                .set("access_token", store.get("token"));
        return request(options);
    }

    public JSONObject requestPermissions() throws IOException, HttpResponseError {
        var options = new RequestOptions(this);
        options.setPath(GRAPH_ORIGIN + "/app/permissions");
        return authenticatedRequest(options);
    }
}
