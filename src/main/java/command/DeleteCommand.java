package command;

// 导入 TaskList
import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import exception.TaskIndexOutOfBoundsException;
import task.Task;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

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


