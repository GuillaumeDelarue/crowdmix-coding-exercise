package gd.crowdmix.data;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class InMemoryRepositoryTest {
    private final InMemoryRepository data = new InMemoryRepository();

    @Test
    public void createNewUser() throws Exception {
        final String userName = "Charlie";
        final User expectedUser = new User(userName);
        assertThat(data.users.contains(expectedUser), is(false));
        assertThat(data.findOrCreateUser(userName), is(equalTo(expectedUser)));
        assertThat(data.users.contains(expectedUser), is(true));
    }

    @Test
    public void findExistingUser() throws Exception {
        final String userName = "Alice";
        final User expectedUser = data.findOrCreateUser(userName);
        assertThat(data.findOrCreateUser(userName), is(sameInstance(expectedUser)));
    }
}