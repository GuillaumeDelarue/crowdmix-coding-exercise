package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.data.WallMessage;
import gd.crowdmix.ui.Output;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.joda.time.Instant;
import org.junit.Test;

import java.util.Arrays;

import static gd.crowdmix.util.TestValuesFactory.aString;
import static gd.crowdmix.util.TestValuesFactory.aWallMessage;

public class RequestWallTest {
    private final Mockery mockery = new Mockery();
    private final Repository data = mockery.mock(Repository.class);
    private final Output output = mockery.mock(Output.class);

    private final String userName = aString(8);
    private final RequestWall command = new RequestWall(userName);
    private final WallMessage wallMessage1 = aWallMessage(new Instant());
    private final WallMessage wallMessage2 = aWallMessage(new Instant());
    private final WallMessage wallMessage3 = aWallMessage(new Instant());
    private final WallMessage wallMessage4 = aWallMessage(new Instant());

    @Test
    public void displayTheWallOfTheUser() {
        mockery.checking(new Expectations() {{
            oneOf(data).userWall(userName);
            will(returnValue(Arrays.asList(wallMessage1, wallMessage2, wallMessage3, wallMessage4)));
        }});
        mockery.checking(new Expectations() {{
            oneOf(output).displayWallMessage(wallMessage1);
            oneOf(output).displayWallMessage(wallMessage2);
            oneOf(output).displayWallMessage(wallMessage3);
            oneOf(output).displayWallMessage(wallMessage4);
        }});

        command.execute(data, output);
        mockery.assertIsSatisfied();
    }
}