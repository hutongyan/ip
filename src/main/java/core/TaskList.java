package core;

import java.util.ArrayList;
import task.Task;
import ui.Ui;
import exception.TaskIndexOutOfBoundsException;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * <p>
 * The list of tasks is stored as an ArrayList of Task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a specified list of tasks.
     *
     * @param tasks The list of tasks to be stored.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task to remove (0-based).
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void deleteTask(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.remove(index);
    }

    /**
     * Marks a task as completed.
     *
     * @param index The index of the task to mark as completed.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void markTask(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task.
     *
     * @param index The index of the task to mark as completed.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void unmarkTask(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.get(index).markAsNotDone();
    }

    /**
     * Displays the list of tasks.
     * <p>
     * If no tasks exist, it informs the user that the list is empty.
     *
     * @param ui The user interface to display messages.
     */
    public void printList(Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage("No tasks available.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

