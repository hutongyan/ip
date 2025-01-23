import java.util.Scanner;

public class Baimi {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Baimi");
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
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex]);
            } else if (command.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex]);
            } else if (command.startsWith("todo ")) {
                String description = command.substring(5);
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else if (command.startsWith("deadline ")) {
                String[] parts = command.substring(9).split(" /by ");
                String description = parts[0];
                String by = parts[1];
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else if (command.startsWith("event ")) {
                String[] parts = command.substring(6).split(" /from ");
                String description = parts[0];
                String[] timeParts = parts[1].split(" /to ");
                String from = timeParts[0];
                String to = timeParts[1];
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else {
                System.out.println("Sorry, I didn't understand that command.");
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
