package ming.command;

import ming.exception.MingException;
import ming.model.Task;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.addTodo(description);
        storage.save(tasks.getTasks());
        return ui.showAdd(task, tasks.getSize());
    }
}
