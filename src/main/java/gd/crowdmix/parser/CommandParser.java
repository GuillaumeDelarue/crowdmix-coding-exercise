package gd.crowdmix.parser;

import gd.crowdmix.command.Command;

public interface CommandParser {
    Command parseCommand(String commandLine);
}
