package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.Task;
import task.Todo;

/**
 * Represents a command to add a todo task to the task list.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     * <p>
     * This command exits the program.
     *
     * @param tasks   The task list to add the task to.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save tasks to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;  // This command exits the program
    }
}
