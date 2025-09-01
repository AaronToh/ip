package ming.command;

import java.time.LocalDateTime;

import ming.exception.MingException;
import ming.model.Task;
import ming.model.TaskList;
import ming.storage.Storage;
import ming.ui.Ui;


/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    /**
     * Constructs a DeadlineCommand with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date and time.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.addDeadline(description, by);
        storage.save(tasks.getTasks());
        ui.showAdd(task, tasks.getSize());
    }
}
