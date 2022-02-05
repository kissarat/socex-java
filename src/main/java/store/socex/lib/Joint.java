package store.socex.lib;

public interface Joint<By, To> {
    By attachedBy(To to);
    By attach(To to);
    void detach(To to);
}
