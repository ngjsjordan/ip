package marquess;

import marquess.command.Command;
import marquess.exception.MarquessException;

/**
 * Main class for Marquess.
 */
public class Marquess {

    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for Marquess.
     *
     * @param filePath File Path to the txt storage file.
     */
    public Marquess(String filePath) {
        parser = new Parser();
        taskList = new TaskList();
        storage = new Storage(filePath);
        ui = new Ui();

        storage.load(taskList);
    }

    /**
     * Runs the program.
     */
    private void run() {
        ui.showWelcome();

        boolean isExit = false;

        while (!isExit) {
            try {
                ui.showLine();
                String input = ui.readCommand();
                Command c = parser.parse(input);
                assert c != null;
                c.execute(storage, taskList, ui);
                isExit = c.isExit();
            } catch (MarquessException e) {
                ui.showException(e);
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            assert c != null;
            String res = c.execute(storage, taskList, ui);
            return res;
        } catch (MarquessException e) {
            return "Something has gone terribly wrong: " + e;
        }
    }

    public static void main(String[] args) {
        new Marquess("./data/marquess.txt").run();
    }

}
