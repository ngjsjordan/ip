package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.Ui;
import marquess.exception.MarquessException;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int idx;

    /**
     * Constructor for command to delete task.
     *
     * @param idx Index of task to be deleted.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws MarquessException {
        String res = taskList.deleteTask(idx);
        ui.show(res);
        storage.save(taskList);
        return res;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
