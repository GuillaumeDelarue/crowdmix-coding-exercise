package gd.crowdmix.data;

import gd.crowdmix.time.TimeProvider;
import org.joda.time.Instant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMemoryRepository implements Repository {
    private final TimeProvider timeProvider;
    final Set<UserDetails> users = new HashSet<>();

    public InMemoryRepository(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    @Override
    public List<Message> userTimeline(String username) {
        return findOrCreateUser(username).timeline();
    }

    @Override
    public List<WallMessage> userWall(String username) {
        return findOrCreateUser(username).wall();
    }

    @Override
    public void follow(String user, String userToFollow) {
        findOrCreateUser(user).follows(findOrCreateUser(userToFollow));
    }

    @Override
    public void publishMessage(String user, String content) {
        findOrCreateUser(user).publish(timeProvider.now(), content);
    }

    private UserDetails findOrCreateUser(String username) {
        final UserDetails newUser = new UserDetails(username);
        if (!users.contains(newUser)) users.add(newUser);
        for (UserDetails user : users) if (user.name().equals(username)) return user;
        throw new RuntimeException("Could not create user");
    }
}
