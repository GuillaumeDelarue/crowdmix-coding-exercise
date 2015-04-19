package gd.crowdmix.acceptance;

import gd.crowdmix.ui.Output;

import java.util.ArrayList;
import java.util.List;

public class AccumulatingTestOutput implements Output {
    final List<String> messages = new ArrayList<>();

    @Override
    public void displayMessage(String message) {
        messages.add(message);
    }
}
