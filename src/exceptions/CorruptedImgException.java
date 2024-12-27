package exceptions;

/**
 * Exception thrown when an image for QR decoding is corrupted.
 */
public class CorruptedImgException extends Exception {
    public CorruptedImgException(String message) {
        super(message);
    }

    public CorruptedImgException(String message, Throwable cause) {
        super(message, cause);
    }
}
