package gd.crowdmix.main;

import gd.crowdmix.command.Command;
import gd.crowdmix.data.Repository;
import gd.crowdmix.parser.CommandParser;
import gd.crowdmix.ui.Output;

public class ApplicationAssembly {
    private Repository data;
    private CommandParser commandParser;
    private Output output;

    public ApplicationAssembly(Repository data, CommandParser commandParser, Output output) {
        this.data = data;
        this.commandParser = commandParser;
        this.output = output;
    }

    public void processCommand(String commandLine) {
        final Command command = commandParser.parseCommand(commandLine);
        command.execute(data);
        command.displayResult(output);
    }
}
