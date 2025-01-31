package nemo;

import nemo.task.Task;
import nemo.task.TaskList;

public class Ui {
    private static final String DIVIDER = "_".repeat(60);
    private static final String GREETING = "Hello I am nemo.Nemo \uD83D\uDC20\uD83C\uDF89, a very friendly and smart fish yay :)";
    private static final String QUESTION = "What can I do for you?";
    private static final String FAREWELL = "Bye Bye, see you soon!";

    public void showWelcome() {
        System.out.println("   " + DIVIDER);
        System.out.println("   " + GREETING);
        System.out.println("   " + QUESTION);
        System.out.println("   " + DIVIDER);
    }

    public void showFarewell() {
        System.out.println("   " + DIVIDER);
        System.out.println("   " + FAREWELL);
        System.out.println("   " + DIVIDER);
    }

    public void showError(String message) {
        System.out.println("   " + DIVIDER);
        System.out.println("   " + message);
        System.out.println("   " + DIVIDER);
    }

    public void showLoadingError() {
        System.out.println("   " + DIVIDER);
        System.out.println("   Error loading tasks. Starting with an empty task list.");
        System.out.println("   " + DIVIDER);
    }

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

    public void showTaskAdded(Task task, int size) {
        System.out.println("   " + DIVIDER);
        System.out.println("   Got it! nemo.task.Task added to your list by nemo.Nemo:");
        System.out.println("      " + task.toString());
        System.out.println("   Now you have " + size + " tasks in your list");
        System.out.println("   " + DIVIDER);
    }

    public void showTaskStatusUpdated(Task task, boolean isDone) {
        System.out.println("   " + DIVIDER);
        System.out.println("   Nice! I've marked this task as " + (isDone ? "done" : "not done") + ":");
        System.out.println("      " + task.toString());
        System.out.println("   " + DIVIDER);
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("   " + DIVIDER);
        System.out.println("   Okay! I've deleted this task for you:");
        System.out.println("      " + task.toString());
        System.out.println("   Now you have " + size + " tasks in your list");
        System.out.println("   " + DIVIDER);
    }
}