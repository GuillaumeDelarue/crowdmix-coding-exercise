package gd.crowdmix.util;

import org.joda.time.Instant;

public class TimeUtils {
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String elapsedTimeSince(Instant since) {
        final long difference = new Instant().getMillis() - since.getMillis();
        if (difference < 2 * SECOND_MILLIS) {
            return "1 second ago";
        } else if (difference < MINUTE_MILLIS) {
            return difference / SECOND_MILLIS + " seconds ago";
        } else if (difference < 2 * MINUTE_MILLIS) {
            return "1 minute ago";
        } else if (difference < 50 * MINUTE_MILLIS) {
            return difference / MINUTE_MILLIS + " minutes ago";
        } else if (difference < 90 * MINUTE_MILLIS) {
            return "1 hour ago";
        } else if (difference < 24 * HOUR_MILLIS) {
            return difference / HOUR_MILLIS + " hours ago";
        } else if (difference < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return difference / DAY_MILLIS + " days ago";
        }
    }
}
