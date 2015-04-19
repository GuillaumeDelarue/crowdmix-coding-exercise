package gd.crowdmix.acceptance;

import gd.crowdmix.time.TimeProvider;
import org.joda.time.Instant;

public class FixedTimeProvider implements TimeProvider {
    private Instant time = new Instant();

    public void nowIs(Instant time) {
        this.time = time;
    }

    @Override
    public Instant now() {
        return time;
    }
}
