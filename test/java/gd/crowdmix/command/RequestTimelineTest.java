package gd.crowdmix.command;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.Repository;
import gd.crowdmix.ui.Output;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.joda.time.Instant;
import org.junit.Test;

import java.util.Arrays;

import static gd.crowdmix.util.TestValuesFactory.aMessage;
import static gd.crowdmix.util.TestValuesFactory.aString;

public class RequestTimelineTest {
    private final Mockery mockery = new Mockery();
    private final Repository data = mockery.mock(Repository.class);
    private final Output output = mockery.mock(Output.class);

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
            oneOf(output).displayTimelineMessage(message1);
            oneOf(output).displayTimelineMessage(message2);
        }});

        command.execute(data, output);
        mockery.assertIsSatisfied();
    }
}