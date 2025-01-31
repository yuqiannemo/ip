import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) throws NemoException {
        if (index < 0 || index >= tasks.size()) {
            throw new NemoException("Task number " + (index + 1) + " does not exist in the list...");
        }
        tasks.remove(index);
    }

    public Task get(int index) throws NemoException {
        if (index < 0 || index >= tasks.size()) {
            throw new NemoException("Task number " + (index + 1) + " does not exist in the list...");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
}