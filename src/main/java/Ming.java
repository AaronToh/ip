import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ming {
    public static void main(String[] args) {
        List<Task> list = loadAll();

        Scanner scanner = new Scanner(System.in);

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
        saveAll(list);
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
        saveAll(list);
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
        saveAll(list);
        System.out.println("I have now added:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public static List<Task> loadAll() {
        List<Task> list = new ArrayList<>();
        Path path = Paths.get("data", "ming.txt");
        Path directory = path.getParent();

        try {
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Error initializing storage: " + e.getMessage());
        }

        Scanner dataScanner;
        try {
            dataScanner = new Scanner(path.toFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (dataScanner.hasNextLine()) {
            String[] parts = dataScanner.nextLine().split(" \\| ");
            boolean done = parts[1].equals("1");
            switch (parts[0]) {
            case "T":
                Task todo = new Todo(parts[2]);
                if (done) {
                    try {
                        todo.markAsDone();
                    } catch (MingException e) {
                        System.out.println("Error marking task as done: " + e.getMessage());
                    }
                }
                list.add(todo);
                break;
            case "D":
                Task deadline = new Deadline(parts[2], parts[3]);
                if (done) {
                    try {
                        deadline.markAsDone();
                    } catch (MingException e) {
                        System.out.println("Error marking task as done: " + e.getMessage());
                    }
                }
                list.add(deadline);
                break;
            case "E":
                Task event = new Event(parts[2], parts[3], parts[4]);
                if (done) {
                    try {
                        event.markAsDone();
                    } catch (MingException e) {
                        System.out.println("Error marking task as done: " + e.getMessage());
                    }
                }
                list.add(event);
                break;
            default:
                System.out.println("Unknown task type in storage: " + parts[0]);
            }
        }
        return list;
    }

    public static void saveAll(List<Task> list) {
        Path path = Paths.get("data", "ming.txt");
        try {
            FileWriter writer = new FileWriter(path.toFile());
            for (Task task : list) {
                writer.write(task.toDataString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}