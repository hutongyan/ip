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
     * Executes the command to add a new task to the task list.
     *
     * @param tasks The task list to which the task will be added.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to save tasks to the file.
     * @throws BaimiException If an error occurs during task addition.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BaimiException {
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:\n  " + task);
        ui.showMessage("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.save(tasks.getTasks());  // Save changes
    }
}

