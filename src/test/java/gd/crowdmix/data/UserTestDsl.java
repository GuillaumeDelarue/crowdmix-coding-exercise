package gd.crowdmix.data;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserTestDsl {

    protected void whenUserPublishesMessages(UserDetails user, Message... messages) {
        for (Message message : messages) {
            user.publish(message.timestamp(), message.content());
        }
    }

    protected void andUserPublishesMessages(UserDetails user, Message... messages) {
        whenUserPublishesMessages(user, messages);
    }

    protected void andUserFollows(UserDetails user, UserDetails followed) {
        user.follows(followed);
    }

    protected void thenUserTimelineShouldBe(UserDetails user, Message... timeline) {
        assertThat(user.timeline(), is(equalTo(Arrays.asList(timeline))));
    }

    protected void andUserWallShouldBe(UserDetails user, WallMessage... wall) {
        assertThat(user.wall(), is(equalTo(Arrays.asList(wall))));
    }
}