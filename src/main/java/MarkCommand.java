public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BaimiException {
        tasks.markTask(taskIndex);
        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.getTasks().get(taskIndex));
        storage.save(tasks.getTasks());
    }
}

