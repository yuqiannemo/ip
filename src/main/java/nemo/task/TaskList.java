package nemo.task;

import java.util.ArrayList;

import nemo.NemoException;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with existing tasks.
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the task list is empty.
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds a task to the list.
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list by index.
     * @param index The index of the task to delete.
     * @throws NemoException If the index is out of bounds.
     */
    public void delete(int index) throws NemoException {
        if (index < 0 || index >= tasks.size()) {
            throw new NemoException("Task number " + (index + 1) + " does not exist in the list...");
        }
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the list by index.
     * @param index The index of the task.
     * @return The task at the given index.
     * @throws NemoException If the index is out of bounds.
     */
    public Task get(int index) throws NemoException {
        if (index < 0 || index >= tasks.size()) {
            throw new NemoException("Task number " + (index + 1) + " does not exist in the list...");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}