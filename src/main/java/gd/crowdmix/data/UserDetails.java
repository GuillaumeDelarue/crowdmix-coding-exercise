package gd.crowdmix.data;

import gd.crowdmix.util.CaseClassOne;
import org.joda.time.Instant;

import java.util.*;

public class UserDetails extends CaseClassOne<String> {
    private final List<Message> timeline = new ArrayList<>();
    private final Set<UserDetails> follows = new HashSet<>();

    public UserDetails(String name) {
        super(name);
    }

    public String name() {
        return $1;
    }

    public void publish(Instant time, String message) {
        timeline.add(new Message(time, message));
    }

    public void follows(UserDetails userToFollow) {
        follows.add(userToFollow);
    }

    public List<Message> timeline() {
        final List<Message> timelineCopy = new ArrayList<>(timeline);
        Collections.sort(timelineCopy);
        return timelineCopy;
    }

    public List<WallMessage> wall() {
        final List<WallMessage> wallMessages = generateWallMessagesFor(this);
        for (UserDetails followed : follows) wallMessages.addAll(generateWallMessagesFor(followed));
        Collections.sort(wallMessages);
        return wallMessages;
    }

    private List<WallMessage> generateWallMessagesFor(UserDetails user) {
        final List<WallMessage> messages = new ArrayList<>();
        for (Message message : user.timeline) messages.add(new WallMessage(user.name(), message));
        return messages;
    }
}
