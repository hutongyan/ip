package command;
import core.TaskList;
import ui.Ui;
import storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    public boolean isExit() {
        return false;  // Most commands do not exit the program
    }
}