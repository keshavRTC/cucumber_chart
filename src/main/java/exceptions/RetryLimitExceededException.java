package exceptions;

public class RetryLimitExceededException extends ChartValidationException {
    public RetryLimitExceededException(String actionDescription, int retries) {
        super(" Retry limit exceeded for: " + actionDescription + ". Attempts: " + retries);
    }

    public RetryLimitExceededException(String actionDescription, int retries, Throwable cause) {
        super(" Retry limit exceeded for: " + actionDescription + ". Attempts: " + retries, cause);
    }
}

