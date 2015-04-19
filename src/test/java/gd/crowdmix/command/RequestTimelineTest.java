package gd.crowdmix.command;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.Repository;
import gd.crowdmix.time.TimeProvider;
import gd.crowdmix.ui.Output;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.joda.time.Instant;
import org.junit.Test;

import java.util.Arrays;

import static gd.crowdmix.util.TestValuesFactory.aMessage;
import static gd.crowdmix.util.TestValuesFactory.aString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RequestTimelineTest {
    private final Mockery mockery = new Mockery();
    private final Repository data = mockery.mock(Repository.class);
    private final Output output = mockery.mock(Output.class);
    private final TimeProvider timeProvider = mockery.mock(TimeProvider.class);

    private final String userName = aString(8);
    private final Message message1 = aMessage(new Instant());
    private final Message message2 = aMessage(new Instant());
    private final RequestTimeline command = new RequestTimeline(userName);

    @Test
    public void displayTheTimelineOfTheUser() {
        mockery.checking(new Expectations() {{
            oneOf(data).userTimeline(userName);
            will(returnValue(Arrays.asList(message1, message2)));
        }});
        mockery.checking(new Expectations() {{
            exactly(4).of(timeProvider).now();
            will(returnValue(new Instant()));
        }});
        mockery.checking(new Expectations() {{
            oneOf(output).displayMessage(command.formatted(message1, timeProvider));
            oneOf(output).displayMessage(command.formatted(message2, timeProvider));
        }});

        command.execute(data, output, timeProvider);
        mockery.assertIsSatisfied();
    }

    @Test
    public void correctFormatting() {
        mockery.checking(new Expectations() {{
            oneOf(timeProvider).now();
            will(returnValue(new Instant()));
        }});
        assertThat(command.formatted(new Message(new Instant().minus(1000000), "this is a message"), timeProvider), is("this is a message (16 minutes ago)"));
        mockery.assertIsSatisfied();
    }
}