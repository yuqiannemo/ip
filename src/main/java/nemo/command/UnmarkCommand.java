package nemo.command;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.Task;
import nemo.task.TaskList;

/**
 * Represents a command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    /** The index of the task to be marked as undone. */
    private int index;

    /**
     * Constructs a new UnmarkCommand.
     *
     * @param messageArray The user input split into an array of strings.
     * @throws NemoException If the task number is missing or invalid.
     */
    public UnmarkCommand(String[] messageArray) throws NemoException {
        if (messageArray.length < 2) {
            throw new NemoException("Opps, please specify a task number after 'unmark'.");
        }
        try {
            this.index = Integer.parseInt(messageArray[1]) - 1;
        } catch (NumberFormatException e) {
            throw new NemoException("Opps :( Invalid index! Maybe try to provide a valid number after 'unmark'?");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        Task task = tasks.get(index);
        task.markAsUndone();
        ui.showTaskStatusUpdated(task, false);
        storage.save(tasks);
    }
}