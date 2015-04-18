package gd.crowdmix.data;

import java.util.List;

import static gd.crowdmix.util.TestValuesFactory.aString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class RepositoryTestDsl {
    protected final InMemoryRepository data = new InMemoryRepository();
    protected final String user = aString(8);
    protected final String message1 = aString(100);
    protected final String message2 = aString(100);
    protected final String message3 = aString(100);
    protected final String message4 = aString(100);

    protected void whenUserHasPublishedMessages(String user, String... messages) {
        for (String message : messages) data.publishMessage(user, message);
    }

    protected void thenTimelineListsTheMessages(String user, String... messages) {
        final List<Message> timeline = data.userTimeline(user);
        assertThat(timeline.size(), is(messages.length));
        for (String message : messages) assertThatMessageIsContainedIn(message, timeline);
    }

    protected void thenWallListsTheMessages(String user, List<String> messages, String followed, List<String> messagesFromFollowed) {
        final List<WallMessage> wall = data.userWall(user);
        assertThat(wall.size(), is(messages.size() + messagesFromFollowed.size()));
        for (String message : messages) assertThatMessageIsContainedIn(message, user, wall);
        for (String message : messagesFromFollowed) assertThatMessageIsContainedIn(message, followed, wall);
    }

    protected void whenUserHasMessagesAndFollowsAnotherUser(String user, List<String> messages, String followed, List<String> messagesFromFollowed) {
        whenUserHasPublishedMessages(user, messages.toArray(new String[messages.size()]));
        data.follow(user, followed);
        whenUserHasPublishedMessages(followed, messagesFromFollowed.toArray(new String[messagesFromFollowed.size()]));
    }

    private void assertThatMessageIsContainedIn(String message, List<Message> timeline) {
        for (Message fromTimeline : timeline) if (fromTimeline.content().equals(message)) return;
        fail(String.format("Message [%s] was not in timeline", message));
    }

    private void assertThatMessageIsContainedIn(String message, String user, List<WallMessage> wall) {
        for (WallMessage fromWall : wall) if (fromWall.user().equals(user) && fromWall.message().content().equals(message)) return;
        fail(String.format("Message [%s - %s] was not in wall", user, message));
    }
}
