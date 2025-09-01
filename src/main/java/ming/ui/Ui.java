package ming.ui;

import java.util.List;
import java.util.Scanner;

import ming.model.Task;

/**
 * Handles interactions with the user via the command line.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a line separator after a response for better readability.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Loading error!");
    }

    public void showWelcome() {
        System.out.println("Hello! I'm ming.app.Ming\n" + "What can I do for you?");
    }

    /**
     * Reads a command from the user.
     */
    public String readCommand() {
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showExit() {
        System.out.println("Bye!");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showList(List<Task> tasks) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    public void showMark(Task task) {
        System.out.println("I've marked this task as done:\n" + task);
    }

    public void showUnmark(Task task) {
        System.out.println("I've unmarked this task:\n" + task);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The new size of the task list after deletion.
     */
    public void showDelete(Task task, int size) {
        System.out.println("I've removed this task:\n" + task
                + "\n" + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays the list of tasks that match a search query.
     *
     * @param tasks The list of matching tasks.
     */
    public void showFind(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
            return;
        }
        int i = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param size The new size of the task list after addition.
     */
    public void showAdd(Task task, int size) {
        System.out.println("I have now added:\n" + task
                + "\n" + "Now you have " + size + " tasks in the list.");
    }
}
