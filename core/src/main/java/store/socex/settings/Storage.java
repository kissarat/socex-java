package store.socex.settings;

import java.io.Closeable;

public interface Storage extends Closeable, Settings {
    void open();
}
