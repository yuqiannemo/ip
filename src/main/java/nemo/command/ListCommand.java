package nemo.command;

import nemo.Storage;
import nemo.Ui;
import nemo.task.TaskList;

/**
 * Represents a command to list the tasks the task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getTaskList(tasks);
    }
}
