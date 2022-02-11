package store.socex.media;

public class MarkupLink {
    private final String url;
    private final String text;

    public MarkupLink(String url) {
        this(url, null);
    }

    public MarkupLink(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }
}
