package gd.crowdmix.time;

import org.joda.time.Instant;

public class CurrentTimeProvider implements TimeProvider {

    @Override
    public Instant now() {
        return new Instant();
    }
}
