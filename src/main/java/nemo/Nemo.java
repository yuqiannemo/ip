package nemo;

import java.util.Scanner;

import nemo.command.Command;
import nemo.task.TaskList;


/**
 * The main class for the Nemo application.
 * Nemo is a task management application that allows users to add, delete, and manage tasks
 * such as ToDos, Deadlines, and Events. Tasks are stored in a file and can be loaded upon startup.
 */
@SuppressWarnings("checkstyle:CommentsIndentation")
public class Nemo {
    /** The list of tasks managed by Nemo. */
    private TaskList tasks;

    /** Handles user interface interactions. */
    private Ui ui;

    /** Manages loading and saving tasks to a file. */
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
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Nemo application.
     * Displays a welcome message and continuously reads user input until the "bye" command is entered.
     * Parses and executes user commands, and displays appropriate feedback or error messages.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            try {
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (NemoException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

//    /**
//     * The entry point of the Nemo application.
//     * Creates a new Nemo instance and starts the application.
//     */
//    public static void main(String[] args) {
//        new Nemo("tasks.txt").run();
//    }

//    public static void main(String[] args) {
//        System.out.println("Hello!");
//    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Nemo heard: " + input;
    }
}
