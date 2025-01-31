package nemo.command;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.Deadline;
import nemo.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    public AddDeadlineCommand(String message) throws NemoException {
        if (!message.contains("/by")) {
            throw new NemoException("Opps :( nemo.task.Deadline needs to include '/by'!");
        }

        String[] parts = message.split("/by", 2);
        if (parts.length < 2 || parts[0].trim().length() < 9) {
            throw new NemoException("Opps :( nemo.command.Command needs to be 'deadline description /by yyyy-mm-dd' format!");
        }

        this.description = parts[0].substring(9).trim();
        String by = parts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new NemoException("Opps :( nemo.task.Deadline description or date cannot be empty!");
        }

        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new NemoException("Opps :( nemo.task.Deadline date is in the wrong format! Use yyyy-mm-dd instead :)");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.showTaskAdded(deadline, tasks.size());
        storage.save(tasks);
    }
}