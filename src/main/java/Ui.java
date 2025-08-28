import java.util.Scanner;

/**
 * Handles interactions with users, including inputs and display.
 */
public class Ui {
    private static final String DIVIDER = "-".repeat(50);

    public Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void show(String s) {
        System.out.println(s);
    }

    public void showException(Exception e) {
        System.err.println(e.getMessage());
    }

    public void showLine() {
        System.out.println(Ui.DIVIDER);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Marquess!");
        System.out.println("What can I do for you?");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
