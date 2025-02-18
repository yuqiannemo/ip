package nemo.gui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import nemo.task.Deadline;
import nemo.task.Event;
import nemo.task.Task;

/**
 * Service to handle task reminders and show alerts when a task is due soon.
 */
public class ReminderService {
    private final Set<Task> remindedTasks = new HashSet<>();

    /**
     * Checks if any tasks are due soon and triggers reminders.
     *
     * @param tasks List of tasks to check.
     */
    public void checkReminders(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            if (task.isDueSoon() && !remindedTasks.contains(task)) {
                showReminder(task);
            }
        }
    }

    /**
     * Displays a reminder alert for the given task.
     *
     * @param task The task that is due soon.
     */
    private void showReminder(Task task) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Task Reminder");
            alert.setHeaderText("Upcoming Task Reminder");
            if (task instanceof Deadline) {
                alert.setContentText("🔔 task: " + task.getDescription() + " is due in one day!");
            } else if (task instanceof Event) {
                alert.setContentText("🔔 event: " + task.getDescription() + " will start in one day!");
            }
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        });
    }

    /**
     * Clears reminders for completed or expired tasks.
     *
     * @param task The task that should be removed from the reminder list.
     */
    public void clearReminder(Task task) {
        remindedTasks.remove(task);
    }
}
