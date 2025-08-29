package ming.model;

import ming.exception.MingException;


public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with the given description. isDone is set to false by default.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() throws MingException {
        if (isDone) {
            throw new MingException("ming.model.Task is already done.");
        }
        this.isDone = true;
    }

    public void markAsNotDone() throws MingException {
        if (!isDone) {
            throw new MingException("ming.model.Task is not done yet.");
        }
        this.isDone = false;
    }

    /**
     * Returns the description of the task formatted for storage.
     */
    public String toDataString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
