package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.Task;
import task.Todo;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BaimiException {
        tasks.unmarkTask(taskIndex);
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.getTasks().get(taskIndex));
        storage.save(tasks.getTasks());
    }
}

