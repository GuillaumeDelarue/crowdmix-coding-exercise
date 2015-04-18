package gd.crowdmix.ui;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.WallMessage;
import org.joda.time.Instant;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleOutputTest {
    private ConsoleOutput ui = new ConsoleOutput();

    @Test
    public void adHocConsoleTest() throws Exception {
        ui.displayTimelineMessage(new Message(new Instant().minus(1000000), "this is a message"));
        ui.displayWallMessage(new WallMessage("Bob", new Message(new Instant().minus(1000), "this is a message")));
    }

    @Test
    public void correctFormatting() throws Exception {
        assertThat(ui.formatted(new Message(new Instant().minus(1000000), "this is a message")), is("this is a message (16 minutes ago)"));
        assertThat(ui.formatted(new WallMessage("Bob", new Message(new Instant().minus(1000), "this is a message"))), is("Bob - this is a message (1 second ago)"));
    }
}