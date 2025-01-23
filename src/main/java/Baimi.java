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
            try {
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
                        if (taskIndex < 0 || taskIndex >= taskCount) {
                            throw new BaimiException("Invalid task number. Please provide a valid task index.");
                        }
                        tasks[taskIndex].markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks[taskIndex]);
                    } catch (NumberFormatException e) {
                        throw new BaimiException("Please provide a valid task number to mark.");
                    }
                } else if (command.startsWith("unmark ")) {
                    try {
                        int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= taskCount) {
                            throw new BaimiException("Invalid task number. Please provide a valid task index.");
                        }
                        tasks[taskIndex].markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks[taskIndex]);
                    } catch (NumberFormatException e) {
                        throw new BaimiException("Please provide a valid task number to unmark.");
                    }
                } else if (command.startsWith("todo ")) {
                    String description = command.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new BaimiException("The description of a todo cannot be empty.");
                    }
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (command.startsWith("deadline ")) {
                    String[] parts = command.substring(9).split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new BaimiException("Invalid deadline format. Use: deadline [description] /by [time].");
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (command.startsWith("event ")) {
                    String[] parts = command.substring(6).split(" /from ");
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        throw new BaimiException("Invalid event format. Use: event [description] /from [start time] /to [end time].");
                    }
                    String description = parts[0].trim();
                    String[] timeParts = parts[1].split(" /to ");
                    if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                        throw new BaimiException("Invalid event format. Use: event [description] /from [start time] /to [end time].");
                    }
                    String from = timeParts[0].trim();
                    String to = timeParts[1].trim();
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else {
                    throw new BaimiException("I'm sorry, but I don't understand that command.");
                }
            } catch (BaimiException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
