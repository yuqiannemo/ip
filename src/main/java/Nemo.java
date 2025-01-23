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
        int count = 1;
        for (Task task : tasks) {
            System.out.println("   " + count + ". " + task.toString());
            count++;
        }
        System.out.println("   " + divider);
    }

    public void MarkAsDone(int index) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("   " + divider);
            System.out.println("   invalid index");
        } else {
            tasks.get(index - 1).markAsDone();
            System.out.println("   " + divider);
            System.out.println("   Nice! I've marked this task as done:");
            System.out.println("      " + tasks.get(index - 1).toString());
            System.out.println("   " + divider);
        }
    }

    public void MarkAsUndone(int index) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("   " + divider);
            System.out.println("   invalid index");
        } else {
            tasks.get(index - 1).markAsUndone();
            System.out.println("   " + divider);
            System.out.println("   Okay! I've marked this task as not done yet:");
            System.out.println("      " + tasks.get(index - 1).toString());
            System.out.println("   " + divider);
        }
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
                        throw new NemoException("Opps :( Deadline must include '/by'!");
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
                        throw new NemoException("Opps :( Event must include '/from' and '/to'!");
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
                    throw new NemoException("I don't know what does that mean... Try typing todo/event/deadline " +
                            "for task management");
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
            } else if (Objects.equals(command, "mark")) {
                nemo.MarkAsDone(Integer.parseInt(messageArray[1]));
            } else if (Objects.equals(command, "unmark")) {
                nemo.MarkAsUndone(Integer.parseInt(messageArray[1]));
            } else {
                nemo.handleTask(message, command);
            }
        }
    }
}
