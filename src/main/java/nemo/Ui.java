package nemo;

import nemo.task.Task;
import nemo.task.TaskList;

/**
 * Handles user interface interactions for the Nemo application.
 * This class is responsible for generating messages and task lists as strings.
 */
public class Ui {
    /** A greeting message displayed when the application starts. */
    private static final String GREETING = "Hello I am Nemo, a very friendly and smart fish yay:)";

    /** A question displayed to prompt the user for input. */
    private static final String QUESTION = "What can I do for you?";

    /** A farewell message displayed when the application exits. */
    private static final String FAREWELL = "Bye Bye, see you soon!";

    /**
     * Generates a welcome message.
     */
    public String getWelcomeMessage() {
        return GREETING + "\n" + QUESTION + "\n";
    }

    /**
     * Generates a farewell message.
     */
    public String getFarewellMessage() {
        return FAREWELL + "\n";
    }

    /**
     * Generates an error message.
     *
     * @param message The error message to be displayed.
     */
    public String getErrorMessage(String message) {
        return message + "\n";
    }

    /**
     * Generates an error message when loading tasks fails.
     */
    public String getLoadingErrorMessage() {
        return "Error loading tasks. Starting with an empty task list.\n";
    }

    /**
     * Generates the list of tasks as a string.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String getTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder("");
        if (tasks.isEmpty()) {
            sb.append("No tasks now, take a rest and come back later :)\n");
        } else {
            int count = 1;
            for (Task task : tasks.getTasks()) {
                sb.append(count++).append(". ").append(task.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Generates a message confirming that a task has been added.
     */
    public String getTaskAddedMessage(Task task, int size) {
        return "Got it! Task added to your list by Nemo:\n  " + task.toString()
                + "\nNow you have " + size + " tasks in your list\n";
    }

    /**
     * Generates a message listing the tasks found by a search query.
     */
    public String getTaskFoundMessage(TaskList tasks) {
        StringBuilder sb = new StringBuilder("");
        if (tasks.isEmpty()) {
            sb.append("No tasks found, take a rest and come back later :)\n");
        } else {
            int count = 1;
            for (Task task : tasks.getTasks()) {
                sb.append(count++).append(". ").append(task.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Generates a message confirming that a task's status has been updated.
     */
    public String getTaskStatusUpdatedMessage(Task task, boolean isDone) {
        return "Nice! I've marked this task as " + (isDone ? "done" : "not done")
                + ":\n  " + task.toString() + "\n";
    }

    /**
     * Generates a message confirming that a task has been deleted.
     */
    public String getTaskDeletedMessage(Task task, int size) {
        return "Okay! I've deleted this task for you:\n  " + task.toString()
                + "\nNow you have " + size + " tasks in your list\n";
    }
}
