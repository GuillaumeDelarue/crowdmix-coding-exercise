package gd.crowdmix.main;

import gd.crowdmix.command.Command;
import gd.crowdmix.data.Repository;
import gd.crowdmix.parser.CommandParser;
import gd.crowdmix.time.TimeProvider;
import gd.crowdmix.ui.Output;

public class ApplicationAssembly {
    private Repository data;
    private CommandParser commandParser;
    private Output output;
    private final TimeProvider timeProvider;

    public ApplicationAssembly(Repository data, CommandParser commandParser, Output output, TimeProvider timeProvider) {
        this.data = data;
        this.commandParser = commandParser;
        this.output = output;
        this.timeProvider = timeProvider;
    }

    public void processCommand(String commandLine) {
        final Command command = commandParser.parseCommand(commandLine);
        command.execute(data, output, timeProvider);
    }
}
