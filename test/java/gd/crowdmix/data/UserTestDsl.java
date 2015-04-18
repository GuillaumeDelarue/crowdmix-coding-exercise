package gd.crowdmix.data;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserTestDsl {

    protected void whenUserPublishesMessages(User user, Message... messages) {
        for (Message message : messages) {
            user.publish(message.timestamp(), message.content());
        }
    }

    protected void andUserPublishesMessages(User user, Message... messages) {
        whenUserPublishesMessages(user, messages);
    }

    protected void andUserFollows(User user, User followed) {
        user.follows(followed);
    }

    protected void thenUserTimelineShouldBe(User user, Message... timeline) {
        assertThat(user.timeline(), is(equalTo(Arrays.asList(timeline))));
    }

    protected void andUserWallShouldBe(User user, WallMessage... wall) {
        assertThat(user.wall(), is(equalTo(Arrays.asList(wall))));
    }
}