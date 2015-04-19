package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.time.TimeProvider;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.TestValuesFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import static gd.crowdmix.util.TestValuesFactory.aString;

public class FollowUserTest {
    private final Mockery mockery = new Mockery();
    private final Repository data = mockery.mock(Repository.class);
    private final Output output = mockery.mock(Output.class);
    private final TimeProvider timeProvider = mockery.mock(TimeProvider.class);

    private final String userName = aString(8);
    private final String followedUserName = aString(8);
    private final FollowUser command = new FollowUser(userName, followedUserName);

    @Test
    public void addFollowedUserToInitialUserAndNoOutput() {
        mockery.checking(new Expectations() {{
            oneOf(data).follow(userName, followedUserName);
        }});

        command.execute(data, output, timeProvider);
        mockery.assertIsSatisfied();
    }
}