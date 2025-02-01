package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.Task;
import task.Todo;

/**
 * Represents a command to mark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a new UnmarkCommand with the given task index.
     *
     * @param taskIndex The index of the task to mark as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command.
     * <p>
     * This command marks the task at the specified index as not done in the task list.
     *
     * @param tasks   The task list to mark the task as not done in.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save tasks to.
     * @throws BaimiException If an error occurs during task unmarking.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BaimiException {
        tasks.unmarkTask(taskIndex);
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.getTasks().get(taskIndex));
        storage.save(tasks.getTasks());
    }
}

