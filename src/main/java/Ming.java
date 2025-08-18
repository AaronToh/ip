import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>(100);

        System.out.println("Hello from! I'm Ming\n" + "What can I do for you?\n");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("list")) {
                int i = 1;
                for (String item : list) {
                    System.out.println(i + ". " + item);
                    i++;
                }
            } else if (input.equals("bye")){
                System.out.println("Goodbye!");
                return;
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}