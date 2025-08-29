package ming.command;

import ming.exception.MingException;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;
import ming.model.Task;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.mark(index);
        storage.save(tasks.getTasks());
        ui.showMark(task);
    }
}
