package exception;

/**
 * The type Input validation failed exception.
 */
public class InputValidationFailedException extends RuntimeException {

    /**
     * Instantiates a new Input validation failed exception.
     *
     * @param message the message to be displayed
     */
    public InputValidationFailedException(String message) {
        super(message);
    }
}
