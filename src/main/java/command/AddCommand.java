package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.Task;
import task.Todo;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {
    private String description;

    /**
     * Constructs an AddCommand with a specified task description.
     *
     * @param description The description of the task to add.
     */
    public AddCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the add command, adding a new task to the task list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws BaimiException If an error occurs while saving the task list.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws BaimiException {
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks.getTasks());

        return "Got it. I've added this task:\n  " + task +
                "\nNow you have " + tasks.getTasks().size() + " tasks in the list.";
    }
}

