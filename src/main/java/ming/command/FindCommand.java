package ming.command;

import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        var foundTasks = tasks.find(keyword);
        ui.showFind(foundTasks);
    }
}
