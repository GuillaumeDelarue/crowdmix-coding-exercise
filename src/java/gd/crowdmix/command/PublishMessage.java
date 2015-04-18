package gd.crowdmix.command;

import gd.crowdmix.data.Repository;
import gd.crowdmix.ui.Output;
import gd.crowdmix.util.CaseClassTwo;

public class PublishMessage extends CaseClassTwo<String, String> implements Command {

    public PublishMessage(String username, String message) {
        super(username, message);
    }

    @Override
    public void execute(Repository data, Output output) {
        data.publishMessage($1, $2);
    }
}
