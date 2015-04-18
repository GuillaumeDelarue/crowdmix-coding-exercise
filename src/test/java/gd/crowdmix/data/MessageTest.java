package gd.crowdmix.data;

import org.joda.time.Instant;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class MessageTest {
    private final Instant time1 = new Instant();
    private final Instant time2 = time1.plus(10000);
    private final String content1 = "I love the weather today";
    private final String content2 = "Damn! We lost!";

    private final Message message = new Message(time1, content1);

    @Test
    public void equalsAndHashcodeDependOnTimestampAndContent() {
        final Message sameMessage = new Message(time1, content1);
        final Message differentMessage = new Message(time2, content2);
        final Message differentMessageWithSameTime = new Message(time1, content2);
        final Message differentMessageWithSameContent = new Message(time2, content1);

        assertThat(message, is(equalTo(sameMessage)));
        assertThat(message, is(not(equalTo(differentMessage))));
        assertThat(message, is(not(equalTo(differentMessageWithSameTime))));
        assertThat(message, is(not(equalTo(differentMessageWithSameContent))));
        assertThat(message.hashCode(), is(equalTo(sameMessage.hashCode())));
    }

    @Test
    public void messageNaturalComparisonOrderIsTimestampStartingFromMostRecent() {
        final Message first = new Message(time1, "first message");
        final Message second = new Message(time1.plus(10000), "second message");
        final Message third = new Message(time1.plus(50000), "third message");

        final List<Message> actual = Arrays.asList(second, first, third);
        Collections.sort(actual);
        assertThat(actual, is(Arrays.asList(third, second, first)));
    }

    @Test
    public void defaultStringRepresentation() {
        assertThat(message.toString(), is(String.format("[%s,%s]", message.timestamp(), message.content())));
    }
}