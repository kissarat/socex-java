package socex.core.scheme;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

public class Injector {
    private static final Injector instance = new Injector();
    private long initializedAt = 0;
    private Scheme scheme;

    public Path getWorkingDirectory() {
        return Paths.get(".").toAbsolutePath().normalize();
    }

    public void init() throws IOException {
        if (initializedAt == 0) {
            var stream = new FileInputStream(getWorkingDirectory().resolve("application.properties").toFile());
            var properties = System.getProperties();
            properties.load(stream);
        } else {
            throw new Error("Already initialized");
        }
            if (null == scheme) {
                scheme = new RedisScheme();
            }
            initializedAt = new Date().getTime();
    }

    public Scheme setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public Scheme getScheme() {
        return scheme;
    }
}
