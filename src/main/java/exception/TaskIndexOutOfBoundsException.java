package exception;

public class TaskIndexOutOfBoundsException extends BaimiException {
    public TaskIndexOutOfBoundsException(int size) {
        super("Task number is out of range. Please enter a valid task number between 1 and " + size + ".");
    }
}
