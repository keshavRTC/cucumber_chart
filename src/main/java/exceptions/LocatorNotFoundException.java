package exceptions;

/**
 * Thrown when a required locator (like iframe, element, or graph config) is not found in JSON or DOM.
 */
public class LocatorNotFoundException extends ChartValidationException {

    public LocatorNotFoundException(String message) {
        super("Locator Not Found: " + message);
    }

    public LocatorNotFoundException(String message, Throwable cause) {
        super("Locator Not Found: " + message, cause);
    }

    // Static factory method (optional, for cleaner usage)
    public static LocatorNotFoundException from(String locatorDescription, Throwable cause) {
        return new LocatorNotFoundException("Unable to find locator: " + locatorDescription, cause);
    }
}
