package nemo.command;

import nemo.NemoException;
import nemo.Storage;
import nemo.Ui;
import nemo.task.TaskList;
import nemo.task.ToDo;

/**
 * Represents a command to add a todo task to the task list.
 */
public class AddTodoCommand extends Command {
    public static final String CANNOT_BE_EMPTY = "Opps :( Todo cannot be empty!";
    /**
     * The description of the todo task.
     */
    private String description;

    /**
     * Constructs a new AddTodoCommand.
     *
     * @param message The user input containing the todo details.
     * @throws NemoException If the input format is invalid.
     */
    public AddTodoCommand(String message) throws NemoException {
        this.description = message.substring(4).trim();
        if (description.isEmpty()) {
            throw new NemoException(CANNOT_BE_EMPTY);
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NemoException {
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        storage.save(tasks);
        return ui.getTaskAddedMessage(todo, tasks.size());
    }
}
