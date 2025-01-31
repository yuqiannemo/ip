public class Task {
    protected String description;
    protected TaskStatus status;
    protected enum TaskStatus {
        DONE, NOT_DONE
    }

    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
    }

    public String getStatusIcon() {
        return (status == TaskStatus.DONE ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        status = TaskStatus.DONE;
    }

    public void markAsUndone() {
        status = TaskStatus.NOT_DONE;
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
