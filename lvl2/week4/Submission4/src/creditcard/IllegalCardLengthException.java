package creditcard;

/**
 * Signals the given credit card number is not of a valid length.
 */
public class IllegalCardLengthException extends Exception {
    /**
     * {@inheritDoc}
     */
    public IllegalCardLengthException(String message) {
        super(message);
    }
}
