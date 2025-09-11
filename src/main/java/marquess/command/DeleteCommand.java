package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.Ui;
import marquess.exception.MarquessException;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final Integer[] indices;

    /**
     * Constructor for command to delete task.
     *
     * @param indices Indices of task to be deleted.
     */
    public DeleteCommand(Integer ... indices) {
        this.indices = indices;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws MarquessException {
        StringBuilder res = new StringBuilder();
        for (int idx : indices) {
            res.append(taskList.deleteTask(idx)).append("\n");
        }
        ui.show(res.toString());
        storage.save(taskList);
        return res.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
