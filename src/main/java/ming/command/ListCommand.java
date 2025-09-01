package ming.command;

import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getTasks());
    }
}
