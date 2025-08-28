package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.Ui;

/**
 * duke.command.Command to list existing tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        String s = taskList.getTaskDisplay();
        ui.show(s);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
