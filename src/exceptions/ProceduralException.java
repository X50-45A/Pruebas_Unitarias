package exceptions;

/**
 * Exception thrown for procedural errors in the flow of the system.
 */
public class ProceduralException extends Exception {
    public ProceduralException(String message) {
        super(message);
    }

    public ProceduralException(String message, Throwable cause) {
        super(message, cause);
    }
}


