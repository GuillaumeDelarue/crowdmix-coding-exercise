package gd.crowdmix.data;

import org.joda.time.Instant;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class UserDetailsTest extends UserTestDsl {
    private final UserDetails charlie = new UserDetails("Charlie");
    private final UserDetails alice = new UserDetails("Alice");

    private final Message message1 = new Message(new Instant(), "I love the weather today");
    private final Message message2 = new Message(new Instant().plus(10000), "Damn! We lost!");
    private final Message message3 = new Message(new Instant().plus(30000), "Good game though.");

    @Test
    public void publishMessagesAndRetrieveThemCorrectlyOrderedByTimeInTimelineAndWall() {
        whenUserPublishesMessages(charlie, message1, message2);
        thenUserTimelineShouldBe(charlie, message2, message1);
        andUserWallShouldBe(charlie, new WallMessage(charlie.name(), message2), new WallMessage(charlie.name(), message1));
    }

    @Test
    public void whenFollowingUsersTheirMessagesAreAddedToWallButNotTimeline() {
        whenUserPublishesMessages(charlie, message1, message3);
        andUserFollows(charlie, alice);
        andUserPublishesMessages(alice, message2);
        thenUserTimelineShouldBe(charlie, message3, message1);
        andUserWallShouldBe(charlie, new WallMessage(charlie.name(), message3), new WallMessage(alice.name(), message2), new WallMessage(charlie.name(), message1));
    }

    @Test
    public void equalsAndHashcodeDependOnName() {
        final UserDetails user = new UserDetails("Bob");
        final UserDetails userWithSameName = new UserDetails("Bob");
        final UserDetails userWithDifferentName = new UserDetails("Charlie");

        assertThat(user, is(equalTo(userWithSameName)));
        assertThat(user, is(not(equalTo(userWithDifferentName))));
        assertThat(user.hashCode(), is(equalTo(userWithSameName.hashCode())));
    }

    @Test
    public void defaultStringRepresentation() {
        assertThat(charlie.toString(), is(String.format("[%s]", charlie.name())));
    }
}