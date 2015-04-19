package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.time.TimeProvider;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassTwo;

public class FollowUser extends CaseClassTwo<String, String> implements Command {

    public FollowUser(String username, String userToFollow) {
        super(username, userToFollow);
    }

    @Override
    public void execute(Repository data, Output output, TimeProvider timeProvider) {
        data.follow($1, $2);
    }
}