package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.data.WallMessage;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassOne;

public class RequestWall extends CaseClassOne<String> implements Command {

    public RequestWall(String username) {
        super(username);
    }

    @Override
    public void execute(Repository data, Output output) {
        for (WallMessage message : data.userWall($1)) {
            output.displayWallMessage(message);
        }
    }
}