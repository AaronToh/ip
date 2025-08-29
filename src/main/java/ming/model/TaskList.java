package ming.model;

import ming.exception.MingException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    List<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new java.util.ArrayList<>();
    }

    /**
     * Initializes a TaskList with the given list of tasks.
     *
     * @param tasks List of tasks to initialize the TaskList with.
     */
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

    public List<Task> find(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }


    private void checkIndex(int i) throws MingException {
        if (i < 0 || i >= tasks.size()) {
            throw new MingException("Index out of bounds: " + (i + 1));
        }
    }
}
