package task;

/**
 * Represents a task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Creates a new task with the specified description and type.
     *
     * @param description The description of the task.
     * @param type The type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }
    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
