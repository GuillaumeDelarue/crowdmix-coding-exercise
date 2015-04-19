package gd.crowdmix.util;

import org.joda.time.Instant;
import org.junit.Test;

import static gd.crowdmix.util.TimeUtils.elapsedTimeBetween;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class TimeUtilsTest {
    private static final Instant NOW = new Instant();
    private static final int TWO_SECONDS = 2 * 1000;
    private static final int ONE_MINUTE = 60 * 1000;
    private static final int TWO_MINUTES = 2 * 60 * 1000;
    private static final int ONE_HOUR = 60 * 60 * 1000;
    private static final int TWO_HOURS = 2 * 60 * 60 * 1000;
    private static final int ONE_DAY = 24 * 60 * 60 * 1000;
    private static final int SEVERAL_DAYS = 19 * 24 * 60 * 60 * 1000;

    @Test
    public void elapsedTimeSinceSpecificDate() {
        assertThat(elapsedTimeBetween(NOW, NOW), is("1 second ago"));
        assertThat(elapsedTimeBetween(NOW, NOW.minus(TWO_SECONDS)), is("2 seconds ago"));
        assertThat(elapsedTimeBetween(NOW, NOW.minus(ONE_MINUTE)), is("1 minute ago"));
        assertThat(elapsedTimeBetween(NOW, NOW.minus(TWO_MINUTES)), is("2 minutes ago"));
        assertThat(elapsedTimeBetween(NOW, NOW.minus(ONE_HOUR)), is("1 hour ago"));
        assertThat(elapsedTimeBetween(NOW, NOW.minus(TWO_HOURS)), is("2 hours ago"));
        assertThat(elapsedTimeBetween(NOW, NOW.minus(ONE_DAY)), is("yesterday"));
        assertThat(elapsedTimeBetween(NOW, NOW.minus(SEVERAL_DAYS)), is("19 days ago"));
    }

    @Test
    public void elapsedTimeOnlyUsesTheBiggestUnitOfTime() throws Exception {
        assertThat(elapsedTimeBetween(NOW, NOW.minus(TWO_MINUTES + TWO_SECONDS)), is("2 minutes ago"));
        assertThat(elapsedTimeBetween(NOW, NOW.minus(ONE_HOUR + TWO_MINUTES + TWO_SECONDS)), is("1 hour ago"));
    }
}