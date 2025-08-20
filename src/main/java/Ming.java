import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>(100);

        System.out.println("Hello! I'm Ming!\n" + "What can I do for you?");

        while (true) {
            String line;
            try {
                line = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Empty input detected. Please enter a command.");
                continue;
            }

            try (Scanner lineScanner = new Scanner(line)) {
                String command = lineScanner.next();

                switch (command) {
                    case "list":
                        handleList(list);
                        break;
                    case "mark":
                        handleMark(lineScanner, list);
                        break;
                    case "unmark":
                        handleUnmark(lineScanner, list);
                        break;
                    case "delete":
                        handleDelete(lineScanner, list);
                        break;
                    case "bye":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        handleAddTask(command, lineScanner, list);
                }
            } catch (MingException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void handleList(List<Task> list) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    private static void handleMark(Scanner lineScanner, List<Task> list) throws MingException {
        if (!lineScanner.hasNextInt()) {
            throw new MingException("Please provide a valid task ID to mark as done.");
        }
        int id = lineScanner.nextInt();
        if (id < 1 || id > list.size()) {
            throw new MingException("Task ID out of range. Please provide a valid ID.");
        }
        Task task = list.get(id - 1);
        task.markAsDone();
        System.out.println("I've marked this task as done:\n" + task);
    }

    private static void handleUnmark(Scanner lineScanner, List<Task> list) throws MingException {
        if (!lineScanner.hasNextInt()) {
            throw new MingException("Please provide a valid task ID to unmark.");
        }
        int id = lineScanner.nextInt();
        if (id < 1 || id > list.size()) {
            throw new MingException("Task ID out of range. Please provide a valid ID.");
        }
        Task task = list.get(id - 1);
        task.markAsNotDone();
        System.out.println("I've unmarked this task:\n" + task);
    }

    private static void handleDelete(Scanner lineScanner, List<Task> list) throws MingException {
        if (!lineScanner.hasNextInt()) {
            throw new MingException("Please provide a valid task ID to delete.");
        }
        int id = lineScanner.nextInt();
        if (id < 1 || id > list.size()) {
            throw new MingException("Task ID out of range. Please provide a valid ID.");
        }
        Task task = list.remove(id - 1);
        System.out.println("I've removed this task:\n" + task
                + "\n" + "Now you have " + list.size() + " tasks in the list.");
    }

    private static void handleAddTask(String command, Scanner lineScanner, List<Task> list) throws MingException {
        String remainder = lineScanner.hasNextLine() ? lineScanner.nextLine().trim() : "";
        Task task;

        switch (command) {
            case "todo":
                if (remainder.isEmpty()) {
                    throw new MingException("The description of a todo cannot be empty.");
                }
                task = new Todo(remainder);
                break;
            case "deadline":
                String[] parts = remainder.split(" /by ", 2);
                if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                    throw new MingException("Usage: deadline <description> /by <date>");
                }
                task = new Deadline(parts[0], parts[1]);
                break;
            case "event":
                String[] fromSplit = remainder.split(" /from ", 2);
                if (fromSplit.length < 2) {
                    throw new MingException("Usage: event <description> /from <start time> /to <end time>");
                }
                String[] toSplit = fromSplit[1].split(" /to ", 2);
                if (toSplit.length < 2 || fromSplit[0].isEmpty() || toSplit[0].isEmpty() || toSplit[1].isEmpty()) {
                    throw new MingException("Usage: event <description> /from <start time> /to <end time>");
                }
                task = new Event(fromSplit[0], toSplit[0], toSplit[1]);
                break;
            default:
                throw new MingException("Unknown command: " + command);
        }
        list.add(task);
        System.out.println("I have now added:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }
}