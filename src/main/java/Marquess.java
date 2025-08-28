import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Marquess {

    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Marquess(String file_path) {
        parser = new Parser();
        taskList = new TaskList();
        storage = new Storage(file_path);
        ui = new Ui();

        storage.load(taskList);
    }

    private void run() {
        ui.showWelcome();

        boolean isExit = false;

        while (!isExit) {
            try {
                ui.showLine();
                String input = ui.readCommand();
                Command c = parser.parse(input);
                c.execute(storage, taskList, ui);
                isExit = c.isExit();
            } catch (MarquessException e) {
                ui.showException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Marquess("./data/marquess.txt").run();
    }

}
