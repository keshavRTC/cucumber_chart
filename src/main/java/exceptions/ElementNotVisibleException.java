package exceptions;

public class ElementNotVisibleException extends ChartValidationException {
    public ElementNotVisibleException(String elementDescription) {
        super("Element not visible or hidden: " + elementDescription);
    }

    public ElementNotVisibleException(String elementDescription, Throwable cause) {
        super("Element not visible or hidden: " + elementDescription, cause);
    }
}