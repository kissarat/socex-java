package socex.core;

public class PropertyToken implements Token {
    private final PropertiesStore store;
    private final String name;

    public PropertyToken(PropertiesStore store, String name) {
        this.store = store;
        this.name = name;
    }

    @Override
    public String getToken() {
        return store.getProperty(name);
    }

    @Override
    public void setToken(String token) throws Exception {
        store.setProperty(name, token);
        store.store();
    }
}
