package gd.crowdmix.util;

import gd.crowdmix.data.Message;
import gd.crowdmix.data.WallMessage;
import org.joda.time.Instant;

import java.util.Random;

public class TestValuesFactory {

    public static WallMessage aWallMessage(Instant time) {
        return new WallMessage(aString(8), aMessage(time));
    }

    public static Message aMessage(Instant time) {
        return new Message(time, aString(50));
    }

    public static String aString(int length) {
        final int leftLimit = 97;
        final int rightLimit = 122;
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int newChar = leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
            buffer.append((char) newChar);
        }

        return buffer.toString();
    }
}
