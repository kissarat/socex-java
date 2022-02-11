package store.socex.settings;

import store.socex.Component;

import java.io.Closeable;

public interface Storage extends Closeable, Settings, Component {
    void open();
}
