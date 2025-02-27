package nemo;

import java.util.Arrays;

import nemo.command.AddDeadlineCommand;
import nemo.command.AddEventCommand;
import nemo.command.AddTodoCommand;
import nemo.command.Command;
import nemo.command.DeleteCommand;
import nemo.command.ExitCommand;
import nemo.command.FindCommand;
import nemo.command.ListCommand;
import nemo.command.MarkCommand;
import nemo.command.UnmarkCommand;

/**
 * Parses user input into commands for the Nemo application.
 * This class is responsible for interpreting user input and returning the corresponding command objects.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param message The user input to be parsed.
     * @return The command corresponding to the user input.
     * @throws NemoException If the user input does not match any known command.
     */
    public static Command parse(String message) throws NemoException {
        assert message != null : "Message cannot be null";
        String[] messageArray = message.split(" ");
        assert messageArray.length > 0 : "messageArray should have at least one element";
        String commandStr = Arrays.stream(message.split(" "))
                .findFirst()
                .map(String::toUpperCase)
                .orElseThrow(() -> new NemoException("Invalid command"));


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
        case "FIND":
            return new FindCommand(
                    Arrays.stream(messageArray).skip(1).toArray(String[]::new)
            );
        default:
            throw new NemoException("Unknown command: " + commandStr.toLowerCase());
        }
    }
}
