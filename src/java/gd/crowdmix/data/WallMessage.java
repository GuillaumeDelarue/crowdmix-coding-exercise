package gd.crowdmix.data;

import gd.crowdmix.util.CaseClassTwo;

public class WallMessage extends CaseClassTwo<User, Message> implements Comparable<WallMessage> {

    public WallMessage(User user, Message message) {
        super(user, message);
    }

    public User user() {
        return $1;
    }

    public Message message() {
        return $2;
    }

    @Override
    public int compareTo(WallMessage that) {
        return message().compareTo(that.message());
    }
}
