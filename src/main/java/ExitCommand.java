/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
