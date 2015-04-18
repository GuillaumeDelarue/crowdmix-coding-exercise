package gd.crowdmix.data;

import java.util.List;

public interface Repository {

    List<Message> userTimeline(String username);

    List<WallMessage> userWall(String username);

    void follow(String user, String userToFollow);

    void publishMessage(String user, String content);
}
