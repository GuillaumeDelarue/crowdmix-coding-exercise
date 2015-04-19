package gd.crowdmix.main;

import gd.crowdmix.data.InMemoryRepository;
import gd.crowdmix.parser.TextCommandParser;
import gd.crowdmix.time.CurrentTimeProvider;
import gd.crowdmix.ui.ConsoleOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    private static final String PROMPT = "> ";

    public static void main(String[] args) throws IOException {
        final ApplicationAssembly assembly = new ApplicationAssembly(
                new InMemoryRepository(),
                new TextCommandParser(),
                new ConsoleOutput(),
                new CurrentTimeProvider());

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.print(PROMPT);
            assembly.processCommand(reader.readLine());
        }
    }
}
