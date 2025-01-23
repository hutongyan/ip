import java.util.Scanner;

public class Baimi {
    public static void main(String[] args) {
        String logo = " ____    _    _   __  __   _ \n"
                + "| __ )  / \\  | | |  \\/  | (_)\n"
                + "|  _ \\ / _ \\ | | | |\\/| | | |\n"
                + "| |_) / ___ \\| | | |  | | | |\n"
                + "|____/_/   \\_\\_| |_|  |_| |_|\n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Baimi\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;
        String command;

        while (true) {
            command = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                if (taskCount == 0) {
                    System.out.println("No tasks available.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
            } else {
                tasks[taskCount] = command;
                taskCount++;
                System.out.println("added: " + command);
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}


