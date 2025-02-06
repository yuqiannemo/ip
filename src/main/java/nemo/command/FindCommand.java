package nemo.command;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.Task;
import nemo.task.TaskList;

/**
 * Represents a command to find certain tasks.
 */
public class FindCommand extends Command {
    private String word;

    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        String searchWord = word.toLowerCase();
        TaskList tasksFound = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(searchWord)) {
                tasksFound.add(task);
            }
        }
        return ui.getTaskFoundMessage(tasksFound);
    }
}
