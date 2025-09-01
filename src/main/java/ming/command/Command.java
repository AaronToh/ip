package ming.command;

import ming.exception.MingException;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command. May mutate tasks and should print via ming.ui.Ui.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MingException;

    /**
     * Returns true if this command exits the program.
     */
    public boolean isExit() {
        return false;
    }
}
