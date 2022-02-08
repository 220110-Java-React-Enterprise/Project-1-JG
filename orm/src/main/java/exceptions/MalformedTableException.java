package exceptions;

public class MalformedTableException extends Exception {
    /**
     * Constructs a MalformedTableException with provided message.
     * @param message message to pass along with exception
     */
    public MalformedTableException(String message) {
        super(message);
    }
}
