public class Task {
    protected String description;
    protected Nemo.TaskStatus status;

    public Task(String description) {
        this.description = description;
        this.status = Nemo.TaskStatus.NOT_DONE;
    }

    public String getStatusIcon() {
        return (status == Nemo.TaskStatus.DONE ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        status = Nemo.TaskStatus.DONE;
    }

    public void markAsUndone() {
        status = Nemo.TaskStatus.NOT_DONE;
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
