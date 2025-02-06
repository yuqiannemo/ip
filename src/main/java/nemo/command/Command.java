package nemo.command;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.TaskList;

/**
 * Represents a command that can be executed by the Nemo application.
 * This is an abstract class that serves as the base for all specific commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks to be operated on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks.
     * @throws NemoException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws NemoException;

    /**
     * Indicates whether the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
