package exceptions;

public class LocatorFormatException extends ChartValidationException {
    public LocatorFormatException(String locatorTemplate, String reason) {
        super("Locator format issue in template: '" + locatorTemplate + "'. Reason: " + reason);
    }

    public LocatorFormatException(String locatorTemplate, String reason, Throwable cause) {
        super("Locator format issue in template: '" + locatorTemplate + "'. Reason: " + reason, cause);
    }



}