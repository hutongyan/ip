package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.Task;
import task.Todo;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a new MarkCommand with the given task index.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command.
     * <p>
     * This command marks the task at the specified index as done in the task list.
     *
     * @param tasks   The task list to mark the task as done in.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save tasks to.
     * @throws BaimiException If an error occurs during task marking.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BaimiException {
        tasks.markTask(taskIndex);
        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.getTasks().get(taskIndex));
        storage.save(tasks.getTasks());
    }
}

