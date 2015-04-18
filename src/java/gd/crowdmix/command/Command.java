package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.ui.Output;

public interface Command {

    void execute(Repository data, Output output);
}
