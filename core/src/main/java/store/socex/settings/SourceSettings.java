package store.socex.settings;

public abstract class SourceSettings extends AbstractSettings {
    @Override
    public Settings derive(String name) {
        return new ChildSettings(this, name);
    }
}
