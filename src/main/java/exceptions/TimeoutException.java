package exceptions;

/**
 * Thrown when an expected condition (like element visibility, clickable, text presence) times out.
 */
public class TimeoutException extends ChartValidationException {

    public TimeoutException(String message) {
        super("Timeout occurred: " + message);
    }

    public TimeoutException(String message, Throwable cause) {
        super("Timeout occurred: " + message, cause);
    }

    public static TimeoutException waitingForElement(String locator) {
        return new TimeoutException("Waiting for element to appear: " + locator);
    }

    public static TimeoutException waitingForText(String expectedText) {
        return new TimeoutException("Waiting for text to be visible: " + expectedText);
    }

    public static TimeoutException condition(String description, Throwable cause) {
        return new TimeoutException(description, cause);
    }
}
