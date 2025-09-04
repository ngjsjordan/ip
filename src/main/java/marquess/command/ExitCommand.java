package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.Ui;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        String exit = "Bye. Hope to see you again soon!";
        ui.show(exit);
        return exit;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
