package nemo.command;

import nemo.Storage;
import nemo.Ui;
import nemo.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}