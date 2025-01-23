import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Nemo {
    String divider = "_".repeat(60);
    String greeting = "Hello I am Nemo, a very friendly and smart fish yay :)";
    String question = "What can I do for you?";
    String farewell = "Bye Bye, see you soon!";
    ArrayList<Task> tasks = new ArrayList<>();

    public void List() {
        System.out.println("   " + divider);
        if (tasks.isEmpty()) {
            System.out.println("   No tasks now, take a rest and come back later :)");
        }
        int count = 1;
        for (Task task : tasks) {
            System.out.println("   " + count + ". " + task.toString());
            count++;
        }
        System.out.println("   " + divider);
    }

    public void updateTaskStatus(String command, String indexStr) {
        try {
            int index;
            try {
                index = Integer.parseInt(indexStr);
            } catch (NumberFormatException e) {
                throw new NemoException("Opps :( Invalid index! Maybe try to provide a valid number after '" + command + "'?");
            }

            if (index < 1 || index > tasks.size()) {
                throw new NemoException("Opps :( Task number " + index + " does not exist in the list...");
            }

            Task task = tasks.get(index - 1);
            if (command.equals("mark")) {
                task.markAsDone();
                System.out.println("   " + divider);
                System.out.println("   Nice! I've marked this task as done:");
            } else {
                task.markAsUndone();
                System.out.println("   " + divider);
                System.out.println("   Okay! I've marked this task as not done yet:");
            }
            System.out.println("      " + task.toString());
            System.out.println("   " + divider);
        } catch (NemoException e) {
            System.out.println("   " + divider);
            System.out.println("   " + e.toString());
            System.out.println("   " + divider);
        }
    }

    public void deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("   " + divider);
        System.out.println("   " + "Task deleted from your list by Nemo:");
        System.out.println("      " + task.toString());
        System.out.println("   Now you have " + tasks.size() + " tasks in your list");
        System.out.println("   " + divider);
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("   " + divider);
        System.out.println("   " + "Got it! Task added to your list by Nemo:");
        System.out.println("      " + task.toString());
        System.out.println("   Now you have " + tasks.size() + " tasks in your list");
        System.out.println("   " + divider);
    }

    public void handleTask(String message, String command) {
        try {
            switch (command) {
                case "todo" -> {
                    String description = message.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new NemoException("Opps :( Todo cannot be empty!");
                    }
                    ToDo todo = new ToDo(description);
                    addTask(todo);
                }
                case "deadline" -> {
                    if (!message.contains("/by")) {
                        throw new NemoException("Opps :( Deadline needs to include '/by'!");
                    }
                    String by = message.substring(message.indexOf("/by") + 4).trim();
                    String description = message.substring(9, message.indexOf(" /by")).trim();
                    if (description.isEmpty() || by.isEmpty()) {
                        throw new NemoException("Deadline description or date cannot be empty!");
                    }
                    Deadline deadline = new Deadline(description, by);
                    addTask(deadline);
                }
                case "event" -> {
                    if (!message.contains("/from") || !message.contains("/to")) {
                        throw new NemoException("Opps :( Event needs to include '/from' and '/to'!");
                    }
                    String from = message.substring(message.indexOf("/from") + 6, message.indexOf(" /to")).trim();
                    String to = message.substring(message.indexOf("/to") + 4).trim();
                    String description = message.substring(6, message.indexOf(" /from")).trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new NemoException("Opps :( Event description, from, or to fields cannot be empty!");
                    }
                    Event event = new Event(description, from, to);
                    addTask(event);
                }
                default -> {
                    throw new NemoException("Sorry, Nemo does not know...");
                }
            }
        } catch (NemoException e) {
            System.out.println("   " + divider);
            System.out.println("   " + e.toString());
            System.out.println("   " + divider);
        }
    }

    public static void main(String[] args) {
        Nemo nemo = new Nemo();
        System.out.println("   " + nemo.divider);
        System.out.println("   " + nemo.greeting);
        System.out.println("   " + nemo.question);
        System.out.println("   " + nemo.divider);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            String[] messageArray = message.split(" ");
            String command = messageArray[0];
            if (Objects.equals(message, "list")) {
                nemo.List();
            } else if (Objects.equals(message, "bye")) {
                System.out.println("   " + nemo.divider);
                System.out.println("   " + nemo.farewell);
                System.out.println("   " + nemo.divider);
                System.exit(0);
            } else if (Objects.equals(command, "mark") || Objects.equals(command, "unmark")) {
                if (messageArray.length < 2) {
                    System.out.println("   " + nemo.divider);
                    System.out.println("   Opps, please specify a task number after '" + command + "'.");
                    System.out.println("   " + nemo.divider);
                } else {
                    nemo.updateTaskStatus(command, messageArray[1]);
                }
            } else if (Objects.equals(command, "delete")) {
                if (messageArray.length < 2) {
                    System.out.println("   " + nemo.divider);
                    System.out.println("   Opps, please specify a task number after 'delete'");
                    System.out.println("   " + nemo.divider);
                } else {
                    int index = Integer.parseInt(messageArray[1]);
                    nemo.deleteTask(index);
                }
            }
            else {
                nemo.handleTask(message, command);
            }
        }
    }
}
