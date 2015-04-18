package gd.crowdmix.data;

import com.sun.istack.internal.NotNull;
import gd.crowdmix.util.CaseClassTwo;

public class WallMessage extends CaseClassTwo<String, Message> implements Comparable<WallMessage> {

    public WallMessage(String username, Message message) {
        super(username, message);
    }

    public String user() {
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
