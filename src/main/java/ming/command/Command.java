package ming.command;

import ming.exception.MingException;
import ming.model.TaskList;
import ming.ui.Ui;
import ming.storage.Storage;

public abstract class Command {
    /**
     * Executes the command. May mutate tasks and should print via ming.ui.Ui.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MingException;

    public boolean isExit() {
        return false;
    }
}
