package gd.crowdmix.command;

import gd.crowdmix.data.User;
import org.jmock.Expectations;
import org.junit.Test;

public class FollowUserTest extends CommandTesting {
    private final String followedUserName = "followed";
    private final FollowUser command = new FollowUser(userName, followedUserName);

    @Test
    public void addFollowedUserToInitialUserAndNoOutput() throws Exception {
        final User followedUser = setupUserWithTimeline(followedUserName, message3, message4);
        mockery.checking(new Expectations() {{
            oneOf(data).findOrCreateUser(userName);
            will(returnValue(user));
        }});
        mockery.checking(new Expectations() {{
            oneOf(data).findOrCreateUser(followedUserName);
            will(returnValue(followedUser));
        }});

        command.execute(data);
        command.displayResult(output);

        // TODO: Test that the user is actually added to follows set! how??? By checking wall?
        mockery.assertIsSatisfied();
    }
}