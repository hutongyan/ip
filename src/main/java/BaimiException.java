public class BaimiException extends Exception {
    public BaimiException(String message) {
        super(message);
    }
}

class EmptyDescriptionException extends BaimiException {
    public EmptyDescriptionException(String commandType) {
        super("The description of a " + commandType + " cannot be empty. Please provide a valid description.");
    }
}

class InvalidFormatException extends BaimiException {
    public InvalidFormatException(String correctFormat) {
        super("Invalid format. Correct format: " + correctFormat);
    }
}

class TaskIndexOutOfBoundsException extends BaimiException {
    public TaskIndexOutOfBoundsException(int size) {
        super("Task number is out of range. Please enter a valid task number between 1 and " + size + ".");
    }
}

class UnknownCommandException extends BaimiException {
    public UnknownCommandException() {
        super("Unknown command. Please enter a valid command (e.g., todo, deadline, event, list, delete).");
    }
}