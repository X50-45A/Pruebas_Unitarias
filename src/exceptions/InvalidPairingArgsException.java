package exceptions;

/**
 * Exception thrown when pairing arguments are invalid.
 */
public class InvalidPairingArgsException extends Exception {
    public InvalidPairingArgsException(String message) {
        super(message);
    }

    public InvalidPairingArgsException(String message, Throwable cause) {
        super(message, cause);
    }
}
