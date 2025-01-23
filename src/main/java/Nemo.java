import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Nemo {
    String divider = "_".repeat(60);
    String greeting = "Hello I am Nemo, a very friendly and smart fish yay :) ";
    String question = "What can I do for you?";
    String farewell = "Bye Bye, see you soon!";
    ArrayList<Task> tasks = new ArrayList<Task>();

    public void Echo(String message) {
        System.out.println("   " + divider);
        System.out.println("   " + message);
        System.out.println("   " + divider);
    }

    public void List() {
        System.out.println("   " + divider);
        int count = 1;
        for (Task task : tasks) {
            System.out.println("   " + count + ". " + task.toString());
            count++;
        }
        System.out.println("   " + divider);
    }

    public void Add(String task) {
        Task newTask = new Task(task);
        tasks.add(newTask);
        System.out.println("   " + divider);
        System.out.println("   added: " + task);
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
            System.out.println("   " + tasks.get(index - 1).toString());
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
            System.out.println("   " + tasks.get(index - 1).toString());
            System.out.println("   " + divider);
        }
    }

    public static void main(String[] args) {
        Nemo nemo = new Nemo();
        System.out.println("   " + nemo.divider);
        System.out.println("   " + nemo.greeting);
        System.out.println("   " + nemo.question);
        System.out.println("   " + nemo.divider);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            String[] messageArray = message.split(" ");
            if (Objects.equals(message, "list")) {
                nemo.List();
                continue;
            }
            else if (Objects.equals(message, "bye")) {
                message = nemo.farewell;
                System.out.println("   " + nemo.divider);
                System.out.println("   " + message);
                System.out.println("   " + nemo.divider);
                System.exit(0);
            }
            else if (Objects.equals(messageArray[0], "mark")) {
                nemo.MarkAsDone(Integer.parseInt(messageArray[1]));
            } else if (Objects.equals(messageArray[0], "unmark")) {
                nemo.MarkAsUndone(Integer.parseInt(messageArray[1]));
            } else {
                nemo.Add(message);
            }
        }
    }
}
