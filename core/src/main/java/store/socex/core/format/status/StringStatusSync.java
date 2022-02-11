package store.socex.core.format.status;

public class StringStatusSync implements StringStatus {
    private String state;
    public StringStatusSync(String state) {
        this.state = state;
    }

    @Override
    public String get() {
        return this.state;
    }

    public synchronized void set(String value) {
        this.state = value;
    }

    @Override
    public String toString() {
        return this.get();
    }
}