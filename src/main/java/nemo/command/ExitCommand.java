package nemo.command;

import nemo.Storage;
import nemo.Ui;
import nemo.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
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