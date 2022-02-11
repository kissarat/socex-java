package store.socex.core;

public interface Subdomain<By, Join extends Joint<By, To>, To extends Domain<By, Join, ?>>
        extends Domain<By, Join, To> {
    To getParent();
}
