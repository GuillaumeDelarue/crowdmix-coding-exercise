package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.time.TimeProvider;
import gd.crowdmix.ui.Output;

public class Exit implements Command {

    @Override
    public void execute(Repository data, Output output, TimeProvider timeProvider) {
        output.exitApplication();
    }
}
