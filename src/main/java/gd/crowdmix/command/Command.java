package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.time.TimeProvider;
import gd.crowdmix.ui.Output;

public interface Command {

    void execute(Repository data, Output output, TimeProvider timeProvider);
}
