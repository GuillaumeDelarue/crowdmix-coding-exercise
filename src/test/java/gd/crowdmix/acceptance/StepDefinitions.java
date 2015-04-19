package gd.crowdmix.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gd.crowdmix.data.InMemoryRepository;
import gd.crowdmix.data.Repository;
import gd.crowdmix.main.ApplicationAssembly;
import gd.crowdmix.parser.CommandParser;
import gd.crowdmix.parser.TextCommandParser;
import org.joda.time.DateTime;
import org.joda.time.Instant;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StepDefinitions {
    private final FixedTimeProvider timeProvider = new FixedTimeProvider();
    private final Repository repository = new InMemoryRepository();
    private final CommandParser commandParser = new TextCommandParser();
    private final AccumulatingTestOutput output = new AccumulatingTestOutput();
    private final ApplicationAssembly testAssembly = new ApplicationAssembly(repository, commandParser, output, timeProvider);

    @Given("^system time is now (\\d+):(\\d+):(\\d+)$")
    public void setSystemTime(String hours, String minutes, String seconds) throws Throwable {
        final DateTime systemDateTime = new DateTime(2015, 4, 18, Integer.parseInt(hours), Integer.parseInt(minutes), Integer.parseInt(seconds));
        timeProvider.nowIs(systemDateTime.toInstant());
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
