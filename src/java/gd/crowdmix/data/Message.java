package gd.crowdmix.data;

import gd.crowdmix.util.CaseClassTwo;
import org.joda.time.Instant;

public class Message extends CaseClassTwo<Instant, String> implements Comparable<Message> {

    public Message(Instant timestamp, String content) {
        super(timestamp, content);
    }

    public Instant timestamp() {
        return $1;
    }

    public String content() {
        return $2;
    }

    @Override
    public int compareTo(Message that) {
        return that.timestamp().compareTo(this.timestamp());
    }
}
