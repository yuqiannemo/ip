package nemo;

import java.util.AbstractMap;
import java.util.Map;

import nemo.command.Command;
import nemo.task.TaskList;


/**
 * The main class for the Nemo application.
 * Nemo is a task management application that allows users to add, delete, and manage tasks
 * such as ToDos, Deadlines, and Events. Tasks are stored in a file and can be loaded upon startup.
 */
public class Nemo {
    /**
     * The list of tasks managed by Nemo.
     */
    private TaskList tasks;

    /**
     * Handles user interface interactions.
     */
    private Ui ui;

    /**
     * Manages loading and saving tasks to a file.
     */
    private Storage storage;

    /**
     * Constructs a new Nemo instance.
     * Initializes the user interface, storage, and task list.
     * If the task file exists, tasks are loaded from the file. Otherwise, a new task list is created.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Nemo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NemoException e) {
            ui.getLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response and return the command for the user's chat message.
     */
    public Map.Entry<String, String> getResponse(String input) {
        assert input != null : "input should not be null";
        String response = "";
        Command command;

        String[] messageArray = input.split(" ");
        String commandStr = messageArray[0].toUpperCase();

        try {
            command = Parser.parse(input);
            response = command.execute(tasks, ui, storage);
            if (command.isExit()) {
                return new AbstractMap.SimpleEntry<>(response, commandStr);
            }
        } catch (NemoException e) {
            response = ui.getErrorMessage(e.getMessage());
        }

        return new AbstractMap.SimpleEntry<>(response, commandStr);
    }
}
