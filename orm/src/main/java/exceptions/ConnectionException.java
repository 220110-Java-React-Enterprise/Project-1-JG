package exceptions;

public class ConnectionException extends Exception {
    /**
     * Constructs a ConnectionException with provided message.
     * @param message message to pass along with exception
     */
    public ConnectionException(String message) {
        super(message);
    }
}
