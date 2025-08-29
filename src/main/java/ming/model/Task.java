package ming.model;

import ming.exception.MingException;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public String toDataString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
