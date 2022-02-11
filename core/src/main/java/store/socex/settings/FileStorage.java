package store.socex.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.socex.utils.FileHelper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;

public class FileStorage extends MapSettings implements Storage {
    private final Logger logger = LoggerFactory.getLogger(FileStorage.class);
    private final String id;
    protected String getStorageFilename() {
        return System.getProperty("user.home") + "/" + id + ".json";
    }

    public FileStorage(String id) {
        this(id, new Hashtable<>());
    }

    public FileStorage(String id, Map<String, String> map) {
        super(map);
        this.id = id;
    }

    @Override
    public void close() throws IOException {
        if (map.size() > 0) {
            FileHelper.writeMap(getStorageFilename(), map);
            logger.info("Save sessions");
        }
        map.clear();
    }

    @Override
    public void open() {
        FileHelper.safeReadMap(Paths.get(getStorageFilename()), map);
    }
}
