package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.Ui;

/**
 * Command to list existing tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        String res = taskList.getTaskDisplay();
        ui.show(res);
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
