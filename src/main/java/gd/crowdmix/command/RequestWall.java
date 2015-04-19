package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.data.WallMessage;
import gd.crowdmix.time.TimeProvider;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassOne;

import static gd.crowdmix.util.TimeUtils.elapsedTimeBetween;

public class RequestWall extends CaseClassOne<String> implements Command {

    public RequestWall(String username) {
        super(username);
    }

    @Override
    public void execute(Repository data, Output output, TimeProvider timeProvider) {
        for (WallMessage message : data.userWall($1)) {
            output.displayMessage(formatted(message, timeProvider));
        }
    }

    String formatted(WallMessage message, TimeProvider timeProvider) {
        return String.format("%s - %s (%s)", message.user(), message.message().content(), elapsedTimeBetween(timeProvider.now(), message.message().timestamp()));
    }
}