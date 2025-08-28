package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.Ui;
import marquess.task.Task;

/**
 * duke.command.Command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for command to add task.
     *
     * @param task duke.task.Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        String res = taskList.addTask(task);
        ui.show(res);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
