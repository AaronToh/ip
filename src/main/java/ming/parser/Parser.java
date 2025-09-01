package ming.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import ming.command.Command;
import ming.command.DeadlineCommand;
import ming.command.DeleteCommand;
import ming.command.EventCommand;
import ming.command.ExitCommand;
import ming.command.FindCommand;
import ming.command.ListCommand;
import ming.command.MarkCommand;
import ming.command.TodoCommand;
import ming.command.UnmarkCommand;
import ming.exception.MingException;

/**
 * Parses user input into the corresponding commands for execution.
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input string.
     * @return The Command object representing the user input.
     * @throws MingException If the input is invalid or cannot be parsed.
     */
    public static Command parse(String input) throws MingException {
        Scanner scanner = new Scanner(input);

        String command = scanner.hasNext() ? scanner.next() : "";
        String remainder = scanner.hasNextLine() ? scanner.nextLine() : "";

        int i;
        switch (command) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            i = parseIndex(remainder);
            return new MarkCommand(i);

        case "unmark":
            i = parseIndex(remainder);
            return new UnmarkCommand(i);

        case "delete":
            i = parseIndex(remainder);
            return new DeleteCommand(i);

        case "find":
            if (remainder.isEmpty()) {
                throw new MingException("The search keyword cannot be empty.");
            }
            return new FindCommand(remainder);

        case "todo":
            if (remainder.isEmpty()) {
                throw new MingException("The description of a todo cannot be empty.");
            }
            return new TodoCommand(remainder);

        case "deadline":
            String[] deadlineParts = remainder.split(" /by ", 2);
            if (deadlineParts.length < 2 || deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
                throw new MingException("Usage: deadline <description> /by <due date>");
            }
            return new DeadlineCommand(deadlineParts[0], parseDateTime(deadlineParts[1]));

        case "event":
            String[] eventParts = remainder.split(" /from | /to ", 3);
            if (eventParts.length < 3 || eventParts[0].isEmpty()
                    || eventParts[1].isEmpty() || eventParts[2].isEmpty()) {
                throw new MingException("Usage: event <description> /from <start time> /to <end time>");
            }
            return new EventCommand(eventParts[0],
                    parseDateTime(eventParts[1]),
                    parseDateTime(eventParts[2]));

        default:
            throw new MingException("Unknown command: " + command);
        }
    }

    private static int parseIndex(String arg) throws MingException {
        try {
            return Integer.parseInt(arg.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MingException("Invalid index: " + arg);
        }
    }

    private static LocalDateTime parseDateTime(String input) throws MingException {
        try {
            return LocalDateTime.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MingException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

}
