package store.socex;

import store.socex.settings.Settings;

import java.util.HashMap;
import java.util.Map;

public class SettingsDomain extends Domain {
    protected final Settings settings;

    public SettingsDomain(Settings settings, Map<String, Object> map) {
        super(map);
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }
}
