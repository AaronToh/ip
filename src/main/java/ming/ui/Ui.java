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
    public String showLine() {
        return "____________________________________________________________";
    }

    public String showLoadingError() {
        return "Loading error!";
    }

    public String showWelcome() {
        return "Hello! I'm Ming\nWhat can I do for you?";
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

    public String showExit() {
        return "Bye!";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String showList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        int i = 1;
        for (Task task : tasks) {
            sb.append(i).append(". ").append(task).append("\n");
            i++;
        }
        return sb.toString().trim();
    }

    public String showMark(Task task) {
        return "I've marked this task as done:\n" + task;
    }

    public String showUnmark(Task task) {
        return "I've unmarked this task:\n" + task;
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The new size of the task list after deletion.
     */
    public String showDelete(Task task, int size) {
        return "I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays the list of tasks that match a search query.
     *
     * @param tasks The list of matching tasks.
     */
    public String showFind(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int i = 1;
        for (Task task : tasks) {
            sb.append(i).append(". ").append(task).append("\n");
            i++;
        }
        return sb.toString().trim();
    }

    public String showError(String message) {
        return message;
    }

    public String showAdd(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }
}
