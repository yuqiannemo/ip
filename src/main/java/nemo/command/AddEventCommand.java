package nemo.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.Event;
import nemo.task.TaskList;

public class AddEventCommand extends Command {
    private String description;
    private LocalDate from;
    private LocalDate to;

    public AddEventCommand(String message) throws NemoException {
        if (!message.contains("/from") || !message.contains("/to")) {
            throw new NemoException("Opps :( nemo.task.Event needs to include '/from' and '/to'!");
        }

        String[] parts = message.split("/from", 2);
        if (parts.length < 2 || parts[0].trim().length() < 6) {
            throw new NemoException("Opps :( nemo.command.Command needs to be 'event description /from yyyy-mm-dd /to yyyy-mm-dd' format!");
        }

        this.description = parts[0].substring(6).trim();
        String[] dateParts = parts[1].split("/to", 2);

        if (dateParts.length < 2) {
            throw new NemoException("Opps :( nemo.task.Event needs to have both '/from' and '/to' dates!");
        }

        String from = dateParts[0].trim();
        String to = dateParts[1].trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new NemoException("Opps :( nemo.task.Event description, from, or to date cannot be empty!");
        }

        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new NemoException("Opps :( nemo.task.Event date is in the wrong format! Use yyyy-mm-dd instead :)");
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