package nemo.command;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.Task;
import nemo.task.TaskList;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(String[] messageArray) throws NemoException {
        if (messageArray.length < 2) {
            throw new NemoException("Opps, please specify a task number after 'mark'.");
        }
        try {
            this.index = Integer.parseInt(messageArray[1]) - 1;
        } catch (NumberFormatException e) {
            throw new NemoException("Opps :( Invalid index! Maybe try to provide a valid number after 'mark'?");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        Task task = tasks.get(index);
        task.markAsDone();
        ui.showTaskStatusUpdated(task, true);
        storage.save(tasks);
    }
}