/**
 * Represents command that does not exist.
 */
public class InvalidCommand extends Command {
    private final String invalidCommand;

    public InvalidCommand(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidCommandException {
        throw new InvalidCommandException(invalidCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}