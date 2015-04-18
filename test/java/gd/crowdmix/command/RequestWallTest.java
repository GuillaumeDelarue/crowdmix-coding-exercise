package gd.crowdmix.command;

import gd.crowdmix.data.User;
import gd.crowdmix.data.WallMessage;
import org.jmock.Expectations;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class RequestWallTest extends CommandTesting {
    private final RequestWall command = new RequestWall(userName);

    @Test
    public void displayTheWallOfTheUser() throws Exception {
        final User foundUser = setupUserWithWall(
                userName, new HashSet<>(Arrays.asList(message1, message2)),
                "followedUser", new HashSet<>(Arrays.asList(message3, message4)));
        mockery.checking(new Expectations() {{
            oneOf(data).findOrCreateUser(userName);
            will(returnValue(foundUser));
        }});
        mockery.checking(new Expectations() {{
            final User followedUser = new User("followedUser");
            oneOf(output).displayWallMessage(new WallMessage(foundUser, message1));
            oneOf(output).displayWallMessage(new WallMessage(foundUser, message2));
            oneOf(output).displayWallMessage(new WallMessage(followedUser, message3));
            oneOf(output).displayWallMessage(new WallMessage(followedUser, message4));
        }});

        command.execute(data);
        command.displayResult(output);
        mockery.assertIsSatisfied();
    }
}