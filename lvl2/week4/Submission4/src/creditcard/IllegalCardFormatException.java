package creditcard;

/**
 * Signals the given credit card number is not of a valid format.
 */
public class IllegalCardFormatException extends Exception {
    /**
     * {@inheritDoc}
     */
    public IllegalCardFormatException(String message) {
        super(message);
    }
}
