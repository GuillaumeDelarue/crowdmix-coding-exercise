package gd.crowdmix.data;

import java.util.HashSet;
import java.util.Set;

public class InMemoryRepository implements Repository {
    final Set<User> users = new HashSet<>();

    @Override
    public User findOrCreateUser(String username) {
        final User newUser = new User(username);
        if (!users.contains(newUser)) users.add(newUser);
        for (User user : users) {
            if (user.name().equals(username))
                return user;
        }
        throw new RuntimeException("Something bad happened");
    }
}
