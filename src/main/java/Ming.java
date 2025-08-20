import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>(100);

        System.out.println("Hello! I'm Ming!\n" + "What can I do for you?");

        while (true) {
            Scanner lineScanner = new Scanner(scanner.nextLine());
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
            lineScanner.close();
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

    private static void handleMark(Scanner lineScanner, List<Task> list) {
        int id = lineScanner.nextInt();
        Task task = list.get(id - 1);
        task.markAsDone();
        System.out.println("I've marked this task as done:\n" + task);
    }

    private static void handleUnmark(Scanner lineScanner, List<Task> list) {
        int id = lineScanner.nextInt();
        Task task = list.get(id - 1);
        task.markAsNotDone();
        System.out.println("I've unmarked this task:\n" + task);
    }

    private static void handleDelete(Scanner lineScanner, List<Task> list) {
        int id = lineScanner.nextInt();
        Task task = list.remove(id - 1);
        System.out.println("I've removed this task:\n" + task
                + "\n" + "Now you have " + list.size() + " tasks in the list.");
    }

    private static void handleAddTask(String command, Scanner lineScanner, List<Task> list) {
        String remainder = lineScanner.nextLine();
        Task task;

        switch (command) {
            case "todo":
                task = new Todo(remainder);
                break;
            case "deadline":
                String[] parts = remainder.split(" /by ", 2);
                task = new Deadline(parts[0], parts[1]);
                break;
            case "event":
                String[] fromSplit = remainder.split(" /from ", 2);
                String[] toSplit = fromSplit[1].split(" /to ", 2);
                task = new Event(fromSplit[0], toSplit[0], toSplit[1]);
                break;
            default:
                System.out.println("Unknown task type: " + command);
                return;
        }
        list.add(task);
        System.out.println("I have now added:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }
}