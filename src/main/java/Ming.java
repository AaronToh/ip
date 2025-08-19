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
            String command = lineScanner.next();

            if (command.equals("mark")) {
                int id = lineScanner.nextInt();
                Task task = list.get(id - 1);
                task.markAsDone();
                System.out.println("I've marked this task as dene:\n" + task.toString());
            }  else if (command.equals("unmark")) {
                int id = lineScanner.nextInt();
                Task task = list.get(id - 1);
                task.markAsNotDone();
                System.out.println("I've unmarked this task as dene:\n" + task.toString());
            } else if (input.equals("list")) {
                int i = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task : list) {
                    System.out.println(task.toString());
                    i++;
                }
            } else if (input.equals("bye")){
                System.out.println("Goodbye!");
                return;
            } else {
                Task task = new Task(input);
                list.add(task);
                System.out.println("added: " + task.getDescription());
            }
        }
    }
}