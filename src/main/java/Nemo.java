import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Nemo {
    String divider = "_".repeat(60);
    String greeting = "Hello I am Nemo, a very friendly and smart fish yay :) ";
    String question = "What can I do for you?";
    String farewell = "Bye Bye, see you soon!";
    ArrayList<String> events = new ArrayList<String>();

    public void Echo(String message) {
        System.out.println("   " + divider);
        System.out.println("   " + message);
        System.out.println("   " + divider);
    }

    public void List() {
        System.out.println("   " + divider);
        int count = 1;
        for (String event : events) {
            System.out.println("   " + count + ". " + event);
            count++;
        }
        System.out.println("   " + divider);
    }

    public void Add(String event) {
        if (Objects.equals(event, "bye")) {
            event = farewell;
        }

        events.add(event);
        System.out.println("   " + divider);
        System.out.println("   added: " + event);
        System.out.println("   " + divider);

        if (Objects.equals(event, farewell)) {
            System.exit(0);
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
            if (Objects.equals(message, "list")) {
                nemo.List();
                continue;
            }
            if (Objects.equals(message, "bye")) {
                message = nemo.farewell;
                System.out.println("   " + nemo.divider);
                System.out.println("   " + message);
                System.out.println("   " + nemo.divider);
                System.exit(0);
            }
            nemo.Add(message);
        }
    }
}
