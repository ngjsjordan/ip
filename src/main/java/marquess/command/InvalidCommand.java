package marquess.command;

import marquess.exception.InvalidCommandException;
import marquess.Storage;
import marquess.TaskList;
import marquess.Ui;

/**
 * Represents command that does not exist.
 */
public class InvalidCommand extends Command {
    private final String invalidCommand;

    /**
     * Constructor for invalid command.
     *
     * @param invalidCommand Attempted command that does not exist.
     */
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