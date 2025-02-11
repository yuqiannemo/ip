package nemo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with deadline date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a new Deadline task.
     * @param description The task description.
     * @param by The due date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getMmmDYyyy();
    }

    private String getMmmDYyyy() {
        return " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
