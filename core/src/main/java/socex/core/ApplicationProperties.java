package socex.core;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

public class ApplicationProperties extends Properties implements PropertiesStore {
    public ApplicationProperties(Properties defaults) {
        super(defaults);
    }

    public ApplicationProperties() {
        super(System.getProperties());
    }

    public Path getUserDir() {
        return Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
    }

    public File getPropertiesFile() {
        return getUserDir().resolve("application.properties").toFile();
    }

    @Override
    public synchronized void load() throws Exception {
        super.load(new FileReader(getPropertiesFile()));
    }

    @Override
    public synchronized void store() throws Exception {
        super.store(new FileWriter(getPropertiesFile()), "Saved at " + new Date().toString());
    }
}
