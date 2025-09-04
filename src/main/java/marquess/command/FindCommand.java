package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.Ui;

/**
 * Command to find tasks where description contains search string.
 */
public class FindCommand extends Command {
    private final String searchString;

    /**
     * Constructor for command to add task.
     *
     * @param searchString string to be searched.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        String res = taskList.findTasks(searchString);
        ui.show(res);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
