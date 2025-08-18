import java.util.Scanner;

public class Ming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from! I'm Ming\n" + "What can I do for you?\n");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                return;
            }
            System.out.println(input);
        }
    }
}