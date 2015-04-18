package gd.crowdmix.data;

import org.joda.time.Instant;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class WallMessageTest {
    private final String user1 = "Charlie";
    private final Message message1 = new Message(new Instant(), "I love the weather today");
    private final Message message2 = new Message(new Instant().plus(10000), "Damn! We lost!");

    private final WallMessage wallMessage = new WallMessage(user1, message1);

    @Test
    public void equalsAndHashcodeDependOnUserAndMessage() {
        final String user2 = "Alice";
        final WallMessage sameWallMessage = new WallMessage(user1, message1);
        final WallMessage differentWallMessage = new WallMessage(user2, message2);
        final WallMessage differentWallMessageWithSameUser = new WallMessage(user1, message2);
        final WallMessage differentWallMessageWithSameMessage = new WallMessage(user2, message1);

        assertThat(wallMessage, is(equalTo(sameWallMessage)));
        assertThat(wallMessage, is(not(equalTo(differentWallMessage))));
        assertThat(wallMessage, is(not(equalTo(differentWallMessageWithSameUser))));
        assertThat(wallMessage, is(not(equalTo(differentWallMessageWithSameMessage))));
        assertThat(wallMessage.hashCode(), is(equalTo(sameWallMessage.hashCode())));
    }

    @Test
    public void wallMessageNaturalComparisonOrderIsSameAsMessage() {
        final Instant baseTime = new Instant();
        final WallMessage first = new WallMessage("1", new Message(baseTime, "first message"));
        final WallMessage second = new WallMessage("3", new Message(baseTime.plus(10000), "second message"));
        final WallMessage third = new WallMessage("2", new Message(baseTime.plus(50000), "third message"));

        final List<WallMessage> actual = Arrays.asList(second, first, third);
        Collections.sort(actual);
        assertThat(actual, is(Arrays.asList(third, second, first)));
    }

    @Test
    public void defaultStringRepresentation() {
        assertThat(wallMessage.toString(), is(String.format("[%s,%s]", wallMessage.user(), wallMessage.message())));
    }
}