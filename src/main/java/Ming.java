import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>(100);

        System.out.println("Hello from! I'm Ming\n" + "What can I do for you?\n");

        while (true) {
            String input = scanner.nextLine();
            Scanner lineScanner = new Scanner(input);

            String command = lineScanner.hasNext() ? lineScanner.next() : "";

            switch (command) {
                case "mark":
                    handleMark(lineScanner, list);
                    break;
                case "unmark":
                    handleUnamrk(lineScanner, list);
                    break;
                case "list":
                    handleList(list);
                    break;
                case "bye":
                    System.out.println("Goodbye!");
                    return;
                default:
                    handleAddTask(input, list);
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

    private static void handleUnamrk(Scanner lineScanner, List<Task> list) {
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

    private static void handleAddTask(String input, List<Task> list) {
        Task task = new Task(input);
        list.add(task);
        System.out.println("added: " + task.getDescription());
    }
}