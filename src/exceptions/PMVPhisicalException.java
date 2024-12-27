package exceptions;

public class PMVPhisicalException extends RuntimeException {
    public PMVPhisicalException(String message) {
        super(message);
    }

    public PMVPhisicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
