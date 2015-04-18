package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.ui.Output;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class FollowUserTest {
    protected final Mockery mockery = new Mockery();
    protected final Repository data = mockery.mock(Repository.class);
    protected final Output output = mockery.mock(Output.class);

    protected final String userName = "Charlie";
    private final String followedUserName = "followed";
    private final FollowUser command = new FollowUser(userName, followedUserName);

    @Test
    public void addFollowedUserToInitialUserAndNoOutput() {
        mockery.checking(new Expectations() {{
            oneOf(data).follow(userName, followedUserName);
        }});

        command.execute(data, output);
        mockery.assertIsSatisfied();
    }
}