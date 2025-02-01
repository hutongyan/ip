package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.Deadline;

/**
 * Represents a command to add a new deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs an AddDeadlineCommand with a specified task description and deadline.
     *
     * @param description The description of the deadline task to add.
     * @param by The deadline of the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a new deadline task to the task list.
     *
     * @param tasks The task list to which the task will be added.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to save tasks to the file.
     * @throws Exception If an error occurs during task addition.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.showMessage("Got it. I've added this task:\n  " + deadline);
        ui.showMessage("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}
