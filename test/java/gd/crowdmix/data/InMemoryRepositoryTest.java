package gd.crowdmix.data;

import org.junit.Test;

import java.util.Arrays;

import static gd.crowdmix.util.TestValuesFactory.aString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryRepositoryTest extends RepositoryTestDsl {

    @Test
    public void createUserIfDoesNotExistWhenRequestingTimeline() {
        assertThat(data.users.contains(new UserDetails(user)), is(false));
        data.userTimeline(user);
        assertThat(data.users.contains(new UserDetails(user)), is(true));
    }

    @Test
    public void userTimelineListsAllPublishedMessages() {
        whenUserHasPublishedMessages(user, message1, message2);
        thenTimelineListsTheMessages(user, message1, message2);
    }

    @Test
    public void userWallListsAllPublishedMessagesFromUserAndFollowedUsers() {
        String followedUser = aString(8);
        whenUserHasMessagesAndFollowsAnotherUser(user, Arrays.asList(message1, message2), followedUser, Arrays.asList(message3, message4));
        thenWallListsTheMessages(user, Arrays.asList(message1, message2), followedUser, Arrays.asList(message3, message4));
    }
}