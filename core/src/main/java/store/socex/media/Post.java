package store.socex.media;

public class Post {
    private String text;
    private MarkupLink link;
    private String photo;

    public Post(String text, MarkupLink link, String photo) {
        this.text = text;
        this.link = link;
        this.photo = photo;
    }

    public Post(String text, String url) {
        this(text, new MarkupLink(url), null);
    }

    public Post(String text) {
        this(text, null, null);
    }

    public MarkupLink getLink() {
        return link;
    }

    public String getLinkURL() {
        var link = getLink();
        if (null == link) {
            return null;
        }
        return link.getUrl();
    }

    public String getText() {
        return text;
    }

    public String getPhoto() {
        return photo;
    }
}
