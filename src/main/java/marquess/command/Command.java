package marquess.command;

import marquess.exception.MarquessException;
import marquess.Storage;
import marquess.TaskList;
import marquess.Ui;

/**
 * Parent class of commands which perform certain operations on tasks, ui or storage.
 */
public abstract class Command {
    /**
     * Execute the command to perform its designated operations.
     *
     * @param taskList duke.TaskList class to be operated on
     * @param ui duke.Ui class to be operated on
     * @param storage duke.Storage class to be operated on
     */
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws MarquessException;

    /**
     * Returns whether to exit the program.
     *
     * @return boolean indicating exit status
     */
    public abstract boolean isExit();
}
