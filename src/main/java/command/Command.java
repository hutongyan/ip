package command;
import core.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Represents a command that can be executed by the Baimi application.
 * <p>
 * Commands are typically created by the parser and executed by the main application.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    public boolean isExit() {
        return false;  // Most commands do not exit the program
    }
}