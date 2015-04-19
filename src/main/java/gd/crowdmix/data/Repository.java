package gd.crowdmix.data;

import gd.crowdmix.time.TimeProvider;

import java.util.List;

public interface Repository {

    List<Message> userTimeline(String username);

    List<WallMessage> userWall(String username);

    void follow(String user, String userToFollow);

    void publishMessage(TimeProvider timeProvider, String user, String content);
}
