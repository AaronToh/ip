import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

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
