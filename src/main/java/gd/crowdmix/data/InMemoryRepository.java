package gd.crowdmix.data;

import gd.crowdmix.time.TimeProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMemoryRepository implements Repository {
    final Set<UserDetails> users = new HashSet<>();

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
    public void publishMessage(TimeProvider timeProvider, String user, String content) {
        findOrCreateUser(user).publish(timeProvider.now(), content);
    }

    private UserDetails findOrCreateUser(String username) {
        for (UserDetails user : users) if (user.name().equals(username)) return user;
        return addNewUser(username);
    }

    private UserDetails addNewUser(String username) {
        final UserDetails newUser = new UserDetails(username);
        users.add(newUser);
        return newUser;
    }
}
