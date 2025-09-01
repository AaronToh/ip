package ming.command;

import ming.exception.MingException;
import ming.model.Task;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.delete(index);
        storage.save(tasks.getTasks());
        ui.showDelete(task, tasks.getSize());
    }
}
