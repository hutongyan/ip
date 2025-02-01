package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import exception.TaskIndexOutOfBoundsException;
import task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a new DeleteCommand with the given task index.
     *
     * @param taskIndex The index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command.
     * <p>
     * This command removes the task at the specified index from the task list.
     *
     * @param tasks   The task list to delete the task from.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save tasks to.
     * @throws BaimiException If an error occurs during task deletion.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BaimiException {
        if (taskIndex < 0 || taskIndex >= tasks.getTasks().size()) {
            throw new TaskIndexOutOfBoundsException(tasks.getTasks().size());
        }
        Task removedTask = tasks.getTasks().remove(taskIndex);
        ui.showMessage("Noted. I've removed this task:\n  " + removedTask);
        ui.showMessage("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}


