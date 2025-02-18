package nemo.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Override
    public boolean isDueSoon() {
        return this.status == TaskStatus.NOT_DONE && LocalDateTime.now().plusDays(1).isAfter(by.atStartOfDay())
                && LocalDateTime.now().isBefore(by.atStartOfDay());
    }
}
