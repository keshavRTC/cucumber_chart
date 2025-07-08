



package exceptions;

public class BothIframeLocatorsFailedException extends ChartValidationException {
    public BothIframeLocatorsFailedException(String chartName, Throwable primaryCause, Throwable secondaryCause) {
        super(" Both iframe locators failed for: " + chartName, secondaryCause != null ? secondaryCause : primaryCause);
        this.addSuppressed(secondaryCause);
    }

    // Static factory (optional)
    public static BothIframeLocatorsFailedException from(String chartName, Throwable primaryCause, Throwable secondaryCause) {
        return new BothIframeLocatorsFailedException(chartName, primaryCause, secondaryCause);
    }
}
