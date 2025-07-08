package exceptions;

public class PrimaryIframeFailedException extends ChartValidationException {
    public PrimaryIframeFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public static PrimaryIframeFailedException from(String locator, Throwable cause) {
        return new PrimaryIframeFailedException("Primary iframe failed at locator: " + locator + ". Trying secondary...", cause);
    }
}