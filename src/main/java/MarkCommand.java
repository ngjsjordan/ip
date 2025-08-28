/**
 * Command to add a task to the task list.
 */
public class MarkCommand extends Command {
    private final int idx;
    private final boolean isDone;

    /**
     * Constructor for command to mark task as complete or incomplete.
     *
     * @param idx Index of task to be marked.
     * @param isDone Whether to mark task as complete
     */
    public MarkCommand(int idx, boolean isDone){
        this.idx = idx;
        this.isDone = isDone;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws MarquessException {
        String res = isDone ? taskList.markTask(idx) : taskList.unmarkTask(idx);
        ui.show(res);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
