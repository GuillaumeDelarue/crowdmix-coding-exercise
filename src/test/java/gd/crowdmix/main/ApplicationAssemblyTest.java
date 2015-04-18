package gd.crowdmix.main;

import gd.crowdmix.command.Command;
import gd.crowdmix.data.Repository;
import gd.crowdmix.parser.CommandParser;
import gd.crowdmix.ui.Output;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class ApplicationAssemblyTest {
    private final Mockery mockery = new Mockery();
    private final Repository data = mockery.mock(Repository.class);
    private final Output output = mockery.mock(Output.class);
    private final CommandParser commandParser = mockery.mock(CommandParser.class);
    private final Command command = mockery.mock(Command.class);

    private final ApplicationAssembly assembly = new ApplicationAssembly(data, commandParser, output);

    @Test
    public void parseAndExecuteCommandThenDisplayResult() {
        final String commandLine = "command line";
        mockery.checking(new Expectations() {{
            oneOf(commandParser).parseCommand(commandLine);
            will(returnValue(command));
        }});
        mockery.checking(new Expectations() {{
            oneOf(command).execute(data, output);
        }});

        assembly.processCommand(commandLine);
        mockery.assertIsSatisfied();
    }
}