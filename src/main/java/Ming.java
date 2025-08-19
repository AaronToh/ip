import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>(100);

        System.out.println("Hello from! I'm Ming\n" + "What can I do for you?\n");

        while (true) {
            Scanner lineScanner = new Scanner(scanner.nextLine());
            String command = lineScanner.hasNext() ? lineScanner.next() : null;

            switch (command) {
                case "mark":
                    handleMark(lineScanner, list);
                    break;
                case "unmark":
                    handleUnmark(lineScanner, list);
                    break;
                case "list":
                    handleList(list);
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

    private static void handleMark(Scanner lineScanner, List<Task> list) {
        int id = lineScanner.nextInt();
        Task task = list.get(id - 1);
        task.markAsDone();
        System.out.println("I've marked this task as done:\n" + task.toString());
    }

    private static void handleUnmark(Scanner lineScanner, List<Task> list) {
        int id = lineScanner.nextInt();
        Task task = list.get(id - 1);
        task.markAsNotDone();
        System.out.println("I've unmarked this task as done:\n" + task.toString());
    }

    private static void handleList(List<Task> list) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println(i + ". " + task.toString());
            i++;
        }
    }

    private static void handleAddTask(String command, Scanner lineScanner, List<Task> list) {
        String remainder = lineScanner.nextLine();
        Task task = null;

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
        System.out.println("I have now added:\n" + task.toString()
                + "\nNow you have " + list.size() + " tasks in the list.");
    }
}