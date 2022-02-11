package store.socex;

import store.socex.settings.SystemSettings;

public class Registry extends SettingsDomain {
    public static final String REGISTRY = "domain";
    public static final String EXECUTOR = "executor";
    public static final String RUN = "run";
    public static final String JOB = "job";
    private static final Domain instance = new Registry();

    protected Registry() {
        super(SystemSettings.getInstance(), createMap());
    }

    public static Domain getInstance() {
        return instance;
    }

    public Subdomain createDomain() {
        return new Subdomain(this, createMap());
    }

    public Subdomain sub(String key) {
        var domain = createDomain();
        instances.put(key, domain);
        return domain;
    }

    @Override
    public Object createInstance(String key) {
        switch (key) {
            case REGISTRY:
                return this;
            case EXECUTOR:
                return Lord.getInstance();
            case RUN:
                return new ListClasses();
            default:
                return null;
        }
    }
}
