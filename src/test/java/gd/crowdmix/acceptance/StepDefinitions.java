package gd.crowdmix.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gd.crowdmix.data.InMemoryRepository;
import gd.crowdmix.data.Repository;
import gd.crowdmix.main.ApplicationAssembly;
import gd.crowdmix.parser.CommandParser;
import gd.crowdmix.parser.TextCommandParser;
import org.joda.time.Instant;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StepDefinitions {
    private final FixedTimeProvider timeProvider = new FixedTimeProvider();
    private final Repository repository = new InMemoryRepository(timeProvider);
    private final CommandParser commandParser = new TextCommandParser();
    private final AccumulatingTestOutput output = new AccumulatingTestOutput();
    private final ApplicationAssembly testAssembly = new ApplicationAssembly(repository, commandParser, output);

    @Given("^next command was issued (.+) ago$")
    public void nextCommandWas(String elapsed) throws Throwable {
        timeProvider.nowIs(new Instant()); // TODO: parse elapsed time
    }

    @When("^processing command (.+)$")
    public void processingCommand(String commandLine) throws Throwable {
        testAssembly.processCommand(commandLine);
    }

    @Then("^output is$")
    public void outputCheck(List<String> expectedOutput) throws Throwable {
        assertThat(output.messages, is(expectedOutput));
    }
}
