import java.util.Scanner;

public class Marquess {
    private static final String DIVIDER = "-".repeat(50);

    private final Scanner sc;
    private String[] tasks = new String[100];
    private int num_tasks = 0;

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

                case "list":
                    for (int i = 0; i < this.num_tasks; i++) {
                        System.out.printf("%d. %s%n", i + 1, this.tasks[i]);
                    }
                    break;

                default:
                    this.tasks[this.num_tasks] = input;
                    this.num_tasks++;
                    System.out.printf("added: %s%n", input);
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
