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
        }
        else {
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
        }
        else {
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
            }
            else if (Objects.equals(message, "bye")) {
                message = nemo.farewell;
                System.out.println("   " + nemo.divider);
                System.out.println("   " + message);
                System.out.println("   " + nemo.divider);
                System.exit(0);
            }
            else if (Objects.equals(command, "mark")) {
                nemo.MarkAsDone(Integer.parseInt(messageArray[1]));
            } else if (Objects.equals(command, "unmark")) {
                nemo.MarkAsUndone(Integer.parseInt(messageArray[1]));
            } else {
                if (Objects.equals(command, "todo")) {
//                retrieve the description
                    String description = message.substring(5);
                    ToDo todo = new ToDo(description);
                    nemo.addTask(todo);
                } else if (Objects.equals(command, "deadline")) {
//                retrieve the string after "/by"
                    String by = message.substring(message.indexOf("/by") + 4);
//                retrieve the description
                    String description = message.substring(9, message.indexOf(" /by"));
                    Deadline deadline = new Deadline(description, by);
                    nemo.addTask(deadline);
                } else if (Objects.equals(command, "event")) {
//                retrieve the "from"
                    String from = message.substring(message.indexOf("/from") + 6, message.indexOf(" /to"));
//                retrieve the "to"
                    String to = message.substring(message.indexOf("/to") + 4);
//                retrieve the description
                    String description = message.substring(6, message.indexOf(" /from"));
                    Event event = new Event(description, from, to);
                    nemo.addTask(event);
                } else {
                    nemo.addTask(new Task(message));
                }
            }
            }
        }
    }
