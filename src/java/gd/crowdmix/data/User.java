package gd.crowdmix.data;

import gd.crowdmix.util.CaseClassOne;
import org.joda.time.Instant;

import java.util.*;

public class User extends CaseClassOne<String> {
    private final List<Message> timeline = new ArrayList<>();
    private final Set<User> follows = new HashSet<>();

    public User(String name) {
        super(name);
    }

    public String name() {
        return $1;
    }

    public void publish(Instant time, String message) {
        timeline.add(new Message(time, message));
    }

    public void follows(User userToFollow) {
        follows.add(userToFollow);
    }

    public List<Message> timeline() {
        final List<Message> copy = new ArrayList<>(timeline);
        Collections.sort(copy);
        return copy;
    }

    public List<WallMessage> wall() {
        final List<WallMessage> wallMessages = generateWallMessagesFor(this);
        for (User followed : follows) {
            wallMessages.addAll(generateWallMessagesFor(followed));
        }
        Collections.sort(wallMessages);
        return wallMessages;
    }

    private List<WallMessage> generateWallMessagesFor(User user) {
        final List<WallMessage> messages = new ArrayList<>();
        for (Message message : user.timeline) {
            messages.add(new WallMessage(user, message));
        }
        return messages;
    }
}
