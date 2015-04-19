package gd.crowdmix.time;

import org.joda.time.Instant;

public interface TimeProvider {

    Instant now();
}
