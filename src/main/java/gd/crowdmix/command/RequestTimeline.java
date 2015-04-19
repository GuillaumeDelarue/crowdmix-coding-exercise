package gd.crowdmix.command;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.Repository;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassOne;

import static gd.crowdmix.util.TimeUtils.elapsedTimeSince;

public class RequestTimeline extends CaseClassOne<String> implements Command {

    public RequestTimeline(String username) {
        super(username);
    }

    @Override
    public void execute(Repository data, Output output) {
        for (Message message : data.userTimeline($1)) {
            output.displayMessage(formatted(message));
        }
    }

    String formatted(Message message) {
        return String.format("%s (%s)", message.content(), elapsedTimeSince(message.timestamp()));
    }
}
