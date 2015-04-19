package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.time.TimeProvider;
import gd.crowdmix.ui.Output;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class ExitTest {
    private final Mockery mockery = new Mockery();
    private final Repository data = mockery.mock(Repository.class);
    private final Output output = mockery.mock(Output.class);
    private final TimeProvider timeProvider = mockery.mock(TimeProvider.class);

    private final Exit command = new Exit();

    @Test
    public void addFollowedUserToInitialUserAndNoOutput() {
        mockery.checking(new Expectations() {{
            oneOf(output).exitApplication();
        }});

        command.execute(data, output, timeProvider);
        mockery.assertIsSatisfied();
    }
}
