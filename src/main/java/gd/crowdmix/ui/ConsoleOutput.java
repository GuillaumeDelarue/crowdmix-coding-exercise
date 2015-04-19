package gd.crowdmix.ui;

public class ConsoleOutput implements Output {

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
