import java.util.Scanner;

public class Marquess {
    private final Scanner sc;
    private static final String DIVIDER = "-".repeat(50);

    public Marquess() {
        this.sc = new Scanner(System.in);
    }

    private void run() {
        this.greet();

        String input = "";
        while (!input.equals("bye")) {
            System.out.println(Marquess.DIVIDER);
            input = this.sc.nextLine();

            switch (input) {
                case "bye":
                    this.exit();
                    break;

                default:
                    System.out.println(input);
            }
        }
    }

    private void greet() {
        System.out.println("Hello! I'm Marquess!");
        System.out.println("What can I do for you?");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        new Marquess().run();
    }

}
