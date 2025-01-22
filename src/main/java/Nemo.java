import java.util.Objects;
import java.util.Scanner;

public class Nemo {
    String divider = "_".repeat(60);
    String greeting = "Hello I am Nemo, a very friendly and smart fish yay :) ";
    String question = "What can I do for you?";
    String farewell = "Bye Bye, see you soon!";
    public void Echo(String message) {
        if (Objects.equals(message, "bye")) {
            message = farewell;
        }
        System.out.println("   " + divider);
        System.out.println("   " + message);
        System.out.println("   " + divider);

        if (Objects.equals(message, farewell)) {
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
            nemo.Echo(message);
        }
    }
}
