package gd.crowdmix.command;

import org.jmock.Expectations;
import org.junit.Test;

public class RequestTimelineTest extends CommandTesting {
    private final RequestTimeline command = new RequestTimeline(userName);

    @Test
    public void displayTheTimelineOfTheUser() throws Exception {
        mockery.checking(new Expectations() {{
            oneOf(data).findOrCreateUser(userName);
            will(returnValue(user));
        }});
        mockery.checking(new Expectations() {{
            oneOf(output).displayTimelineMessage(message1);
            oneOf(output).displayTimelineMessage(message2);
        }});

        command.execute(data);
        command.displayResult(output);
        mockery.assertIsSatisfied();
    }
}