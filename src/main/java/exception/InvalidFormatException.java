package exception;

public class InvalidFormatException extends BaimiException {
    public InvalidFormatException(String correctFormat) {
        super("Invalid format. Correct format: " + correctFormat);
    }
}
