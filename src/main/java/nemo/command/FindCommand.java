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
    private String[] words;

    public FindCommand(String... words) {
        this.words = words;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        TaskList tasksFound = new TaskList();
        for (Task task : tasks.getTasks()) {
            boolean isMatch = false;
            for (String word : words) {
                if (task.getDescription().toLowerCase().contains(word.toLowerCase())) {
                    isMatch = true;
                    break;
                }
            }
            if (isMatch) {
                tasksFound.add(task);
            }
        }
        return ui.getTaskFoundMessage(tasksFound);
    }
}
