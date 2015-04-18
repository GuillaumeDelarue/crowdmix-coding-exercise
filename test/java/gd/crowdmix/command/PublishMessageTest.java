package gd.crowdmix.command;

import org.jmock.Expectations;
import org.junit.Test;

public class PublishMessageTest extends CommandTesting {
    private final String message = "Test message";
    private final PublishMessage command = new PublishMessage(userName, message);

    @Test
    public void addFollowedUserToInitialUserAndNoOutput() throws Exception {
        mockery.checking(new Expectations() {{
            oneOf(data).findOrCreateUser(userName);
            will(returnValue(user));
        }});

        command.execute(data);
        command.displayResult(output);

        // TODO: Test that the user is actually added to follows set! how??? By checking wall?
        mockery.assertIsSatisfied();
    }
}