package store.socex.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * My class.
 *
 * @author Taras Labiak <kissarat@gmail.com>
 */
public class Lord implements Runnable {
    static Lord instance;
    protected final Registry registry;

    /** The logger for this class. */
    private static final Logger logger = LoggerFactory.getLogger(Lord.class);

    public Lord() {
        this(new Registry());
    }

    public Lord(Registry registry) {
        this.registry = registry;
        instance = this;
    }

    public static Lord create(String[] args) {
        instance = new Lord();
        return instance;
    }

    @Override
    public void run() {

    }
}
