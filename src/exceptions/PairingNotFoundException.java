package exceptions;

/**
 * Exception thrown when a pairing is not found on the server.
 */
public class PairingNotFoundException extends Exception {
    public PairingNotFoundException(String message) {
        super(message);
    }

    public PairingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
