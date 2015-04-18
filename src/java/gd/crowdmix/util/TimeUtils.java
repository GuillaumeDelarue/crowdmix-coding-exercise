package gd.crowdmix.util;

import org.joda.time.Instant;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class TimeUtils {

    // TODO Bug when multiple things, add space? Wants to see only one!
    public static String elapsedTimeSince(Instant since) {
        final PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendSeconds().appendSuffix(" second ago ", " seconds ago ")
                .appendMinutes().appendSuffix(" minute ago ", " minutes ago ")
                .appendHours().appendSuffix(" hour ago ", " hours ago ")
                .appendDays().appendSuffix(" day ago ", " days ago ")
                .appendWeeks().appendSuffix(" week ago ", " weeks ago ")
                .appendMonths().appendSuffix(" month ago ", " months ago ")
                .appendYears().appendSuffix(" year ago ", " years ago ")
                .printZeroNever()
                .toFormatter();
        return formatter.print(new Period(since, new Instant()));
    }
}
