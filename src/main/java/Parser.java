import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String message) throws NemoException {
        String[] messageArray = message.split(" ");
        String commandStr = messageArray[0].toUpperCase();

        switch (commandStr) {
            case "LIST":
                return new ListCommand();
            case "BYE":
                return new ExitCommand();
            case "MARK":
                return new MarkCommand(messageArray);
            case "UNMARK":
                return new UnmarkCommand(messageArray);
            case "DELETE":
                return new DeleteCommand(messageArray);
            case "TODO":
                return new AddTodoCommand(message);
            case "DEADLINE":
                return new AddDeadlineCommand(message);
            case "EVENT":
                return new AddEventCommand(message);
            default:
                throw new NemoException("Unknown command: " + commandStr.toLowerCase());
        }
    }
}