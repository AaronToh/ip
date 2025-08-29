public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MingException {
        Task task = tasks.addTodo(description);
        storage.save(tasks.getTasks());
        ui.showAdd(task, tasks.getSize());
    }
}
