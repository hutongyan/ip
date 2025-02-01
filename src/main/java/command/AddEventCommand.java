package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.*;

/**
 * Represents a command to add a new event task to the task list.
 */
public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an AddEventCommand with a specified task description and event time.
     *
     * @param description The description of the event task to add.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add a new event task to the task list.
     *
     * @param tasks The task list to which the task will be added.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to save tasks to the file.
     * @throws Exception If an error occurs during task addition.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Event event = new Event(description, from, to);
        tasks.addTask(event);
        ui.showMessage("Got it. I've added this task:\n  " + event);
        ui.showMessage("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}