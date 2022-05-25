package socex.core;

public class PropertyError extends Exception {
    private final String name;

    public PropertyError(String name) {
        super(String.format("Property %s is required", name));
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
