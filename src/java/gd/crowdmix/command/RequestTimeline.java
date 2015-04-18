package gd.crowdmix.command;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.Repository;
import gd.crowdmix.data.User;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassOne;

public class RequestTimeline extends CaseClassOne<String> implements Command {
    private User user = null;

    public RequestTimeline(String username) {
        super(username);
    }

    @Override
    public void execute(Repository data) {
        user = data.findOrCreateUser($1);
    }

    @Override
    public void displayResult(Output output) {
        for (Message message : user.timeline()) {
            output.displayTimelineMessage(message);
        }
    }
}
