package nemo.command;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.Event;
import nemo.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand extends Command {
    /** The description of the event task. */
    private String description;

    /** The start date of the event. */
    private LocalDate from;

    /** The end date of the event. */
    private LocalDate to;

    /**
     * Constructs a new AddEventCommand.
     *
     * @param message The user input containing the event details.
     * @throws NemoException If the input format is invalid or the dates are in the wrong format.
     */
    public AddEventCommand(String message) throws NemoException {
        if (!message.contains("/from") || !message.contains("/to")) {
            throw new NemoException("Opps :( Event needs to include '/from' and '/to'!");
        }

        String[] parts = message.split("/from", 2);
        if (parts.length < 2 || parts[0].trim().length() < 6) {
            throw new NemoException("Opps :( Command needs to be 'event description /from yyyy-mm-dd /to yyyy-mm-dd' format!");
        }

        this.description = parts[0].substring(6).trim();
        String[] dateParts = parts[1].split("/to", 2);

        if (dateParts.length < 2) {
            throw new NemoException("Opps :( Event needs to have both '/from' and '/to' dates!");
        }

        String from = dateParts[0].trim();
        String to = dateParts[1].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new NemoException("Opps :( Event description, from, or to date cannot be empty!");
        }

        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new NemoException("Opps :( Event date is in the wrong format! Use yyyy-mm-dd instead :)");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        Event event = new Event(description, from, to);
        tasks.add(event);
        ui.showTaskAdded(event, tasks.size());
        storage.save(tasks);
    }
}