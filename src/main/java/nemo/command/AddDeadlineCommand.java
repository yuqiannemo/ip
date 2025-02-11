package nemo.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.Deadline;
import nemo.task.TaskList;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String NEEDS_TO_INCLUDE_BY = "Opps :( Deadline needs to include '/by'!";
    public static final String COMMAND_IN_WRONG_FORMAT = "Opps :( Command needs to be "
            + "'deadline description /by yyyy-mm-dd' format!";
    public static final String DATE_CANNOT_BE_EMPTY = "Opps :( Deadline description or date cannot be empty!";
    public static final String DATE_IN_WRONG_FORMAT = "Opps :( Deadline date is"
            + " in the wrong format! Use yyyy-mm-dd instead :)";
    /**
     * The description of the deadline task.
     */
    private String description;

    /**
     * The deadline.
     */
    private LocalDate by;

    /**
     * Constructs a new AddDeadlineCommand.
     *
     * @param message The user input containing the task details.
     * @throws NemoException If the input format is invalid or the dates are in the wrong format.
     */
    public AddDeadlineCommand(String message) throws NemoException {
        if (!message.contains("/by")) {
            throw new NemoException(NEEDS_TO_INCLUDE_BY);
        }

        String[] parts = message.split("/by", 2);
        if (parts.length < 2 || parts[0].trim().length() < 9) {
            throw new NemoException(COMMAND_IN_WRONG_FORMAT);
        }

        this.description = parts[0].substring(9).trim();
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new NemoException(DATE_CANNOT_BE_EMPTY);
        }

        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new NemoException(DATE_IN_WRONG_FORMAT);
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        storage.save(tasks);
        return ui.getTaskAddedMessage(deadline, tasks.size());
    }
}
