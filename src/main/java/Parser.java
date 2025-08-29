import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static Command parse(String input) throws MingException {
        Scanner scanner = new Scanner(input);

        String command = scanner.next();
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

        case "todo":
            if (remainder.isEmpty()) {
                throw new MingException("The description of a todo cannot be empty.");
            }
            return new TodoCommand(remainder);

        case "deadline":
            String[] deadlineParts = remainder.split(" /by ", 2);
            if (deadlineParts.length < 2 || deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
                throw new MingException("The description and deadline of a deadline cannot be empty.");
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

    public static LocalDateTime parseDateTime(String input) throws MingException {
        try {
            return LocalDateTime.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MingException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

}
