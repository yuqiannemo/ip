package nemo.command;

import nemo.Storage;
import nemo.Ui;
import nemo.task.TaskList;

/**
 * Represents a command to exit.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getFarewellMessage();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return True, indicating that this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
