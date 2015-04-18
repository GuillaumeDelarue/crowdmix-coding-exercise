package gd.crowdmix.command;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.Repository;
import gd.crowdmix.data.User;
import gd.crowdmix.ui.Output;
import org.jmock.Mockery;
import org.joda.time.Instant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CommandTesting {
    protected final Mockery mockery = new Mockery();
    protected final Repository data = mockery.mock(Repository.class);
    protected final Output output = mockery.mock(Output.class);

    protected final Message message1 = new Message(new Instant(), "message 1");
    protected final Message message2 = new Message(new Instant().minus(500), "message 2");
    protected final Message message3 = new Message(new Instant().minus(1000), "message 3");
    protected final Message message4 = new Message(new Instant().minus(1500), "message 4");

    protected final String userName = "Charlie";
    protected final User user = setupUserWithTimeline(userName, message1, message2);

    protected User setupUserWithTimeline(String username, Message... messages) {
        return setupUserWithWall(username, new HashSet<>(Arrays.asList(messages)), null, new HashSet<Message>());
    }

    protected User setupUserWithWall(String username, Set<Message> messages, String followedUserName, Set<Message> messagesFromFollowed) {
        final User user = new User(username);
        for (Message message : messages) {
            user.publish(message.timestamp(), message.content());
        }

        if (followedUserName != null) {
            final User followedUser = new User(followedUserName);
            user.follows(followedUser);
            for (Message message : messagesFromFollowed) {
                followedUser.publish(message.timestamp(), message.content());
            }
        }
        return user;
    }
}
