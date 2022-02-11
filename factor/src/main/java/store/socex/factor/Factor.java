package store.socex.factor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public final class Factor implements Runnable {

    /** The logger for this class. */
    private static final Logger logger = LoggerFactory.getLogger(Factor.class);

    @Override
    public void run() {
        System.out.println("Hello Factor");
    }
}
