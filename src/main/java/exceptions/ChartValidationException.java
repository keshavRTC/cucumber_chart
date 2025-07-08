package exceptions;

/**
 * Base exception for all chart-related test validation errors.
 */
public class ChartValidationException extends RuntimeException {

    public ChartValidationException(String message) {
        super(message);
    }

    public ChartValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    // Static factory methods (reusable in other exceptions if needed)
    public static ChartValidationException dataMismatch(String expected, String actual) {
        return new ChartValidationException("Validation failed! Expected: " + expected + ", but got: " + actual);
    }

    public static ChartValidationException invalidLocatorTemplate(String template, String details) {
        return new ChartValidationException("Invalid locator template: " + template + " — " + details);
    }

    public static ChartValidationException sliceNotClickable(String xpath) {
        return new ChartValidationException("Slice found but not clickable. XPath: " + xpath);
    }

    public static ChartValidationException percentageNotFound(String chartName, String details) {
        return new ChartValidationException("Percentage text not found in chart: " + chartName + ". Details: " + details);
    }
}
