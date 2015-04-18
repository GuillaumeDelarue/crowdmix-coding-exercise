package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassTwo;

public class FollowUser extends CaseClassTwo<String, String> implements Command {

    public FollowUser(String username, String userToFollow) {
        super(username, userToFollow);
    }

    @Override
    public void execute(Repository data) {
        data.findOrCreateUser($1).follows(data.findOrCreateUser($2));
    }

    @Override
    public void displayResult(Output output) {
        // No output
    }
}