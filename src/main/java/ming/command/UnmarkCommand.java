package ming.command;

import ming.exception.MingException;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;
import ming.model.Task;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.unmark(index);
        storage.save(tasks.getTasks());
        ui.showUnmark(task);
    }
}
