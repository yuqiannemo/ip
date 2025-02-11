package nemo.command;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.Task;
import nemo.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    /**
     * The index of the task to be marked as done.
     */
    private int index;

    /**
     * Constructs a new MarkCommand.
     *
     * @param messageArray The user input split into an array of strings.
     * @throws NemoException If the task number is missing or invalid.
     */
    public MarkCommand(String[] messageArray) throws NemoException {
        if (messageArray.length < 2) {
            throw new NemoException(MISSING_NUMBER + "'mark'.");
        }
        try {
            this.index = Integer.parseInt(messageArray[1]) - 1;
        } catch (NumberFormatException e) {
            throw new NemoException(INVALID_NUMBER + "'mark'?");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        Task task = tasks.get(index);
        task.markAsDone();
        storage.save(tasks);
        return ui.getTaskStatusUpdatedMessage(task, true);
    }
}
