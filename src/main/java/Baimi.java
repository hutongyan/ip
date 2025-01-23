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
        Task[] tasks = new Task[100];
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
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                }
            } else if (command.startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks[taskIndex]);
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number.");
                }
            } else if (command.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks[taskIndex]);
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number.");
                }
            } else {
                tasks[taskCount] = new Task(command);
                taskCount++;
                System.out.println("added: " + command);
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}

