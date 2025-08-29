import java.time.LocalDateTime;
import java.util.List;

public class TaskList {
    List<Task> tasks;

    public TaskList() {
        this.tasks = new java.util.ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task mark(int index) throws MingException {
        checkIndex(index);
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmark(int index) throws MingException {
        checkIndex(index);
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    public Task addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        return task;
    }

    public Task addDeadline(String description, LocalDateTime by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        return task;
    }

    public Task addEvent(String description, LocalDateTime from, LocalDateTime to) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        return task;
    }

    public Task delete(int index) throws MingException {
        checkIndex(index);
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    private void checkIndex(int i) throws MingException {
        if (i < 0 || i >= tasks.size()) {
            throw new MingException("Index out of bounds: " + (i + 1));
        }
    }
}
