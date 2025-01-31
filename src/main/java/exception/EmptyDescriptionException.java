package exception;

public class EmptyDescriptionException extends BaimiException {
    public EmptyDescriptionException(String commandType) {
        super("The description of a " + commandType + " cannot be empty. Please provide a valid description.");
    }
}
