package gd.crowdmix.ui;

public class ConsoleOutput implements Output {

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void exitApplication() {
        System.out.println("Goodbye !");
        System.exit(0);
    }
}
