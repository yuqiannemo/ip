package nemo.command;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.TaskList;
import nemo.task.ToDo;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String message) throws NemoException {
        this.description = message.substring(4).trim();
        if (description.isEmpty()) {
            throw new NemoException("Opps :( Todo cannot be empty!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        ui.showTaskAdded(todo, tasks.size());
        storage.save(tasks);
    }
}