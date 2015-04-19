package gd.crowdmix.command;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.Repository;
import gd.crowdmix.time.TimeProvider;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassOne;
import org.joda.time.Instant;

import static gd.crowdmix.util.TimeUtils.elapsedTimeBetween;

public class RequestTimeline extends CaseClassOne<String> implements Command {

    public RequestTimeline(String username) {
        super(username);
    }

    @Override
    public void execute(Repository data, Output output, TimeProvider timeProvider) {
        for (Message message : data.userTimeline($1)) {
            output.displayMessage(formatted(message, timeProvider));
        }
    }

    String formatted(Message message, TimeProvider timeProvider) {
        return String.format("%s (%s)", message.content(), elapsedTimeBetween(timeProvider.now(), message.timestamp()));
    }
}
