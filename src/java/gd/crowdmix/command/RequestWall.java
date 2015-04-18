package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.data.User;
import gd.crowdmix.data.WallMessage;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassOne;

public class RequestWall extends CaseClassOne<String> implements Command {
    private User user = null;

    public RequestWall(String username) {
        super(username);
    }

    @Override
    public void execute(Repository data) {
        user = data.findOrCreateUser($1);
    }

    @Override
    public void displayResult(Output output) {
        for (WallMessage message : user.wall()) {
            output.displayWallMessage(message);
        }
    }
}