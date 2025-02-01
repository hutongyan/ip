package core;

import java.util.ArrayList;
import task.Task;
import ui.Ui;
import exception.TaskIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.remove(index);
    }

    public void markTask(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException(tasks.size());
        }
        tasks.get(index).markAsNotDone();
    }

    /**
     * Finds tasks that contain a specific keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

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

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

