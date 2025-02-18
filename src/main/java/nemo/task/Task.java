package nemo.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected TaskStatus status;

    /**
     * Enumeration for task status
     */
    protected enum TaskStatus {
        DONE, NOT_DONE
    }

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "[X]" if the task is done, otherwise "[ ]".
     */
    public String getStatusIcon() {
        return (status == TaskStatus.DONE ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        status = TaskStatus.DONE;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        status = TaskStatus.NOT_DONE;
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public boolean isDueSoon() {
        return false;
    }
}
