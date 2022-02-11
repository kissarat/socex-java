package store.socex.factor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.socex.core.Component;
import store.socex.core.Lord;

/**
 * App driver.
 *
 * @author Taras Labiak <kissarat@gmail.com>
 */
public final class Factor extends  implements Runnable, Component {

    /** The logger for this class. */
    private static final Logger logger = LoggerFactory.getLogger(Factor.class);

    /**
     * Private Constructor.
     */
    private Factor() {
    }

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        Lord.create(args).run();
    }

    @Override
    public void run() {

    }
}
