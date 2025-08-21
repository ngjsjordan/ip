import java.util.Scanner;
import java.util.stream.Stream;

public class Marquess {
    private static final String DIVIDER = "-".repeat(50);

    private final Scanner sc;
    private final TaskList tasks;

    public Marquess() {
        this.sc = new Scanner(System.in);
        this.tasks = new TaskList();
    }

    private void run() {
        this.greet();

        String input = "";
        String[] params;

        while (!input.equals("bye")) {
            System.out.println(Marquess.DIVIDER);
            input = this.sc.nextLine();
            params = input.split(" ");

            switch (params[0]) {
                case "bye":
                    this.exit();
                    break;

                case "list":
                    this.tasks.printTasks();
                    break;

                case "mark":
                    this.tasks.markTask(Integer.parseInt(params[1]) - 1);
                    break;

                case "unmark":
                    this.tasks.unmarkTask(Integer.parseInt(params[1]) - 1);
                    break;

                default:
                    this.tasks.addTask(new Task(input));
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
