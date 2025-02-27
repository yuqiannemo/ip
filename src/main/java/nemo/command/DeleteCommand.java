package nemo.command;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.Task;
import nemo.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * The index of the task to be deleted.
     */
    private int index;

    /**
     * Constructs a new DeleteCommand.
     *
     * @param messageArray The user input split into an array of strings.
     * @throws NemoException If the task number is missing or invalid.
     */
    public DeleteCommand(String[] messageArray) throws NemoException {
        if (messageArray.length < 2) {
            throw new NemoException(MISSING_NUMBER + "'delete'.");
        }
        try {
            this.index = Integer.parseInt(messageArray[1]) - 1;
        } catch (NumberFormatException e) {
            throw new NemoException(INVALID_NUMBER + "'delete'?");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        Task task = tasks.get(index);
        tasks.delete(index);
        storage.save(tasks);
        return ui.getTaskDeletedMessage(task, tasks.size());
    }
}
