package socex.core.media.facebook;

import socex.core.Store;
import socex.core.media.HttpPoster;

public class FacebookAuth extends HttpPoster  {
    public FacebookAuth(Store store) {
        super(store);
    }

    public void setAccessToken(String token) {
        store.set("token", token);
    }

    public String getGrantAccessTokenURL() {
        return String.format("https://graph.facebook.com/oauth/access_token?grant_type=fb_exchange_token&client_id=%s&client_secret=%s&fb_exchange_token=SHORT-LIVED-USER-ACCESS-TOKEN", store.get("id"), store.get("secret"));
    }
}
