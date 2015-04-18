package gd.crowdmix.command;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.Repository;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassOne;

public class RequestTimeline extends CaseClassOne<String> implements Command {

    public RequestTimeline(String username) {
        super(username);
    }

    @Override
    public void execute(Repository data, Output output) {
        for (Message message : data.userTimeline($1)) {
            output.displayTimelineMessage(message);
        }
    }
}
