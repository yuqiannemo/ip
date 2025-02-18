package nemo.task;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task.
     *
     * @param description The task description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
