package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.ui.Output;

public class Exit implements Command {

    @Override
    public void execute(Repository data) {
        System.exit(0);
    }

    @Override
    public void displayResult(Output output) {
        // No Output
    }
}
