package gd.crowdmix.parser;

import com.sun.deploy.util.StringUtils;
import gd.crowdmix.command.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleInputCommandParser implements CommandParser {
    final static String WALL_COMMAND = "wall";
    final static String FOLLOWS_COMMAND = "follows";
    final static String PUBLISH_OPERATOR = "->";

    @Override
    public Command parseCommand(String commandLine) {
        final List<String> tokens = new ArrayList<>(Arrays.asList(commandLine.split(" ")));
        if (tokens.size() == 1) {
            return new RequestTimeline(tokens.get(0));
        } else if (tokens.size() == 2 && tokens.get(1).toLowerCase().equals(WALL_COMMAND)) {
            return new RequestWall(tokens.get(0));
        } else if (tokens.size() > 2 && tokens.get(1).equals(PUBLISH_OPERATOR)) {
            final String username = tokens.remove(0);
            tokens.remove(0);
            return new PublishMessage(username, StringUtils.join(tokens, " "));
        } else if (tokens.size() == 3 && tokens.get(1).equals(FOLLOWS_COMMAND)) {
            return new FollowUser(tokens.get(0), tokens.get(2));
        }
        throw new IllegalArgumentException("Unknown command: [" + commandLine + "]");
    }
}
