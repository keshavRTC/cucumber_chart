package exceptions;

/**
 * Custom exception thrown when a required file is missing.
 * Typically used to wrap FileNotFoundException in a test automation framework.
 */
public class FileNotFoundCustomException extends ChartValidationException {

    public FileNotFoundCustomException(String filePath) {
        super("File not found: " + filePath);
    }

    public FileNotFoundCustomException(String filePath, Throwable cause) {
        super("File not found: " + filePath, cause);
    }
}