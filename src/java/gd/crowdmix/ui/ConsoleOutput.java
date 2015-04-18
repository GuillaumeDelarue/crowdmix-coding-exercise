package gd.crowdmix.ui;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.WallMessage;

import static gd.crowdmix.util.TimeUtils.elapsedTimeSince;

public class ConsoleOutput implements Output {

    @Override
    public void displayTimelineMessage(Message message) {
        System.out.println(formatted(message));
    }

    @Override
    public void displayWallMessage(WallMessage message) {
        System.out.println(formatted(message));
    }

    String formatted(Message message) {
        return String.format("%s (%s)", message.content(), elapsedTimeSince(message.timestamp()));
    }

    String formatted(WallMessage message) {
        return String.format("%s - %s (%s)", message.user(), message.message().content(), elapsedTimeSince(message.message().timestamp()));
    }
}
