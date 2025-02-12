package nemo.gui;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import nemo.NemoException;
import nemo.Storage;
import nemo.task.Task;

/**
 * Handles scheduling of task reminders using a background thread.
 */
public class ReminderScheduler {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final ReminderService reminderService = new ReminderService();


    /**
     * Starts the scheduler that periodically checks for upcoming task reminders.
     * The scheduler runs every hour.
     */
    public static void startScheduler() {
        scheduler.scheduleAtFixedRate(() -> {
            Storage storage = new Storage("tasks.txt");
            ArrayList<Task> tasks = null;
            try {
                tasks = storage.load();
            } catch (NemoException e) {
                throw new RuntimeException(e);
            }
            reminderService.checkReminders(tasks);
        }, 0, 1, TimeUnit.HOURS);
    }

    /**
     * Stops the scheduler and shuts down the background thread.
     * This should be called when the application exits.
     */
    public static void stopScheduler() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(3, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }
}
