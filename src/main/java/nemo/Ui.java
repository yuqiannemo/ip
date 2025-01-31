package nemo;

import nemo.task.Task;
import nemo.task.TaskList;

/**
 * Handles user interface interactions for the Nemo application.
 * This class is responsible for displaying messages and task lists to the user.
 */
public class Ui {
    /** A divider used to separate sections of the output. */
    private static final String DIVIDER = "_".repeat(60);

    /** A greeting message displayed when the application starts. */
    private static final String GREETING = "Hello I am Nemo \uD83D\uDC20\uD83C\uDF89, a very friendly and smart fish yay :)";

    /** A question displayed to prompt the user for input. */
    private static final String QUESTION = "What can I do for you?";

    /** A farewell message displayed when the application exits. */
    private static final String FAREWELL = "Bye Bye, see you soon!";

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("   " + DIVIDER);
        System.out.println("   " + GREETING);
        System.out.println("   " + QUESTION);
        System.out.println("   " + DIVIDER);
    }

    /**
     * Displays a farewell message to the user.
     */
    public void showFarewell() {
        System.out.println("   " + DIVIDER);
        System.out.println("   " + FAREWELL);
        System.out.println("   " + DIVIDER);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("   " + DIVIDER);
        System.out.println("   " + message);
        System.out.println("   " + DIVIDER);
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        System.out.println("   " + DIVIDER);
        System.out.println("   Error loading tasks. Starting with an empty task list.");
        System.out.println("   " + DIVIDER);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("   " + DIVIDER);
        if (tasks.isEmpty()) {
            System.out.println("   No tasks now, take a rest and come back later :)");
        }
        int count = 1;
        for (Task task : tasks.getTasks()) {
            System.out.println("   " + count + ". " + task.toString());
            count++;
        }
        System.out.println("   " + DIVIDER);
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks in the list after adding the new task.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("   " + DIVIDER);
        System.out.println("   Got it! Task added to your list by Nemo:");
        System.out.println("      " + task.toString());
        System.out.println("   Now you have " + size + " tasks in your list");
        System.out.println("   " + DIVIDER);
    }

    /**
     * Displays a message confirming that a task's status has been updated.
     *
     * @param task The task whose status was updated.
     * @param isDone Whether the task was marked as done or not done.
     */
    public void showTaskStatusUpdated(Task task, boolean isDone) {
        System.out.println("   " + DIVIDER);
        System.out.println("   Nice! I've marked this task as " + (isDone ? "done" : "not done") + ":");
        System.out.println("      " + task.toString());
        System.out.println("   " + DIVIDER);
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The total number of tasks in the list after deleting the task.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("   " + DIVIDER);
        System.out.println("   Okay! I've deleted this task for you:");
        System.out.println("      " + task.toString());
        System.out.println("   Now you have " + size + " tasks in your list");
        System.out.println("   " + DIVIDER);
    }
}