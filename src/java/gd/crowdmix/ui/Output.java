package gd.crowdmix.ui;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.WallMessage;

public interface Output {

    void displayTimelineMessage(Message message);

    void displayWallMessage(WallMessage message);
}
