import java.util.Arrays;
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

            try {
                switch (params[0]) {
                    case "bye":
                        this.exit();
                        break;

                    case "list":
                        this.tasks.printTasks();
                        break;

                    case "mark":
                        if (params.length < 2) {
                            throw new InsufficientParametersException("required - task number");
                        }
                        this.tasks.markTask(Integer.parseInt(params[1]) - 1);
                        break;

                    case "unmark":
                        if (params.length < 2) {
                            throw new InsufficientParametersException("required - task number");
                        }
                        this.tasks.unmarkTask(Integer.parseInt(params[1]) - 1);
                        break;

                    case "todo":
                        this.tasks.addTask(new Todo(
                            Arrays.stream(params)
                                .skip(1)
                                .reduce((acc, word) -> acc + " " + word)
                                .orElse(""))
                        );
                        break;

                    case "deadline":
                        this.tasks.addTask(new Deadline(
                            Arrays.stream(params)
                                .skip(1)
                                .takeWhile(word -> !word.equals("/by"))
                                .reduce((acc, word) -> acc + " " + word)
                                .orElse(""),
                            Arrays.stream(params)
                                .dropWhile(word -> !word.equals("/by"))
                                .skip(1)
                                .reduce((acc, word) -> acc + " " + word)
                                .orElse(""))
                        );
                        break;

                    case "event":
                        this.tasks.addTask(new Event(
                            Arrays.stream(params)
                                .skip(1)
                                .takeWhile(word -> !(word.equals("/from") || word.equals("/to")))
                                .reduce((acc, word) -> acc + " " + word)
                                .orElse(""),
                            Arrays.stream(params)
                                .dropWhile(word -> !word.equals("/from"))
                                .skip(1)
                                .takeWhile(word -> !word.equals("/to"))
                                .reduce((acc, word) -> acc + " " + word)
                                .orElse(""),
                            Arrays.stream(params)
                                .dropWhile(word -> !word.equals("/to"))
                                .skip(1)
                                .reduce((acc, word) -> acc + " " + word)
                                .orElse(""))
                        );
                        break;

                    case "delete":
                        if (params.length < 2) {
                            throw new InsufficientParametersException("required - task number");
                        }
                        this.tasks.deleteTask(Integer.parseInt(params[1]) - 1);
                        break;

                    default:
                        throw new InvalidCommandException(params[0]);

                }
            } catch (InsufficientParametersException | InvalidCommandException | TaskNotFoundException e) {
                System.err.println(e);
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
