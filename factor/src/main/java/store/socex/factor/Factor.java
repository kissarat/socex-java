package store.socex.factor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.socex.Component;
import store.socex.stereotype.Entrypoint;

/**
 * App driver.
 *
 * @author Taras Labiak <kissarat@gmail.com>
 */
@Entrypoint("store.socex")
public final class Factor implements Runnable, Component {

    /** The logger for this class. */
    private static final Logger logger = LoggerFactory.getLogger(Factor.class);

    @Override
    public void run() {
        System.out.println("Hello Factor");
    }
}
