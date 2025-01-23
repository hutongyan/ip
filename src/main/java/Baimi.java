import java.util.ArrayList;
import java.util.Scanner;
public class Baimi {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Baimi");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String command;

        while (true) {
            try {
                command = scanner.nextLine().trim();
                System.out.println("____________________________________________________________");

                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (command.equals("list")) {
                    handleListCommand(tasks);
                } else if (command.startsWith("mark ")) {
                    handleMarkCommand(command, tasks);
                } else if (command.startsWith("unmark ")) {
                    handleUnmarkCommand(command, tasks);
                } else if (command.startsWith("todo ")) {
                    handleTodoCommand(command, tasks);
                } else if (command.startsWith("deadline ")) {
                    handleDeadlineCommand(command, tasks);
                } else if (command.startsWith("event ")) {
                    handleEventCommand(command, tasks);
                } else if (command.startsWith("delete ")) {
                    handleDeleteCommand(command, tasks);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (BaimiException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }

    private static void handleListCommand(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void handleMarkCommand(String command, ArrayList<Task> tasks) throws TaskIndexOutOfBoundsException, InvalidFormatException {
        int taskIndex = getTaskIndex(command, "mark ", tasks.size());
        tasks.get(taskIndex).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskIndex));
    }

    private static void handleUnmarkCommand(String command, ArrayList<Task> tasks) throws TaskIndexOutOfBoundsException, InvalidFormatException {
        int taskIndex = getTaskIndex(command, "unmark ", tasks.size());
        tasks.get(taskIndex).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(taskIndex));
    }

    private static void handleTodoCommand(String command, ArrayList<Task> tasks) throws EmptyDescriptionException {
        String description = command.substring(5).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        tasks.add(new Todo(description));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadlineCommand(String command, ArrayList<Task> tasks) throws InvalidFormatException {
        String[] parts = command.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new InvalidFormatException("deadline [description] /by [time]");
        }
        tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleEventCommand(String command, ArrayList<Task> tasks) throws InvalidFormatException {
        String[] parts = command.substring(6).split(" /from ");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new InvalidFormatException("event [description] /from [start time] /to [end time]");
        }
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new InvalidFormatException("event [description] /from [start time] /to [end time]");
        }
        tasks.add(new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeleteCommand(String command, ArrayList<Task> tasks) throws TaskIndexOutOfBoundsException, InvalidFormatException {
        int taskIndex = getTaskIndex(command, "delete ", tasks.size());
        Task removedTask = tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static int getTaskIndex(String command, String prefix, int size) throws TaskIndexOutOfBoundsException, InvalidFormatException {
        try {
            int taskIndex = Integer.parseInt(command.substring(prefix.length()).trim()) - 1;
            if (taskIndex < 0 || taskIndex >= size) {
                throw new TaskIndexOutOfBoundsException(size);
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new InvalidFormatException(prefix + "[task number]");
        }
    }
}