package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.data.User;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassTwo;
import org.joda.time.Instant;

public class PublishMessage extends CaseClassTwo<String, String> implements Command {

    public PublishMessage(String username, String message) {
        super(username, message);
    }

    @Override
    public void execute(Repository data) {
        data.findOrCreateUser($1).publish(new Instant(), $2);
    }

    @Override
    public void displayResult(Output output) {
        // no output
    }
}
