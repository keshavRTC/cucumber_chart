package exceptions;

public class SecondaryIframeFailedException extends ChartValidationException {
    public SecondaryIframeFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public static SecondaryIframeFailedException from(String locator, Throwable cause) {
        return new SecondaryIframeFailedException("Secondary iframe also failed at locator: " + locator, cause);
    }
}