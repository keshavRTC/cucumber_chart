package pages;

import exceptions.*;
import exceptions.TimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.JsonLocatorReader;

import java.time.Duration;
import java.util.List;

public class ChartPage {

    private WebDriver driver;
    private JsonLocatorReader locatorReader;
    private String currentChartName;
    private WebDriverWait wait;

    public ChartPage(WebDriver driver) {
        this.driver = driver;
        this.locatorReader = new JsonLocatorReader();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }




/*
    public void switchToChartIframe(String chartName) {
        this.currentChartName = chartName;
        List<String> iframeLocators = locatorReader.getIframeLocators();
        int index = locatorReader.getGraphIndex(chartName);

        if (iframeLocators.isEmpty()) {
            throw new LocatorNotFoundException("No iframe locators found in JSON.");
        }

        String primaryLocator = iframeLocators.get(0).replace("${index}", String.valueOf(index));
        String secondaryLocator = iframeLocators.size() > 1
                ? iframeLocators.get(1).replace("${index}", String.valueOf(index))
                : null;

        Exception primaryEx = null;
        Exception secondaryEx = null;

        boolean flag = false;

        // Try Primary
        try {
            WebElement iframe = driver.findElement(By.xpath(primaryLocator));
            driver.switchTo().frame(iframe);
            System.out.println(" Switched to iframe using primary locator: " + primaryLocator);
            flag = true;
            return;
        } catch (Exception e) {
            primaryEx = new PrimaryIframeFailedException(primaryLocator, e);
            WebElement iframe = driver.findElement(By.xpath(secondaryLocator));
            driver.switchTo().frame(iframe);
            flag = true;
            System.out.println(" Switched to iframe using secondary locator: " + secondaryLocator);

//            try {
//
//            } catch (Exception ex) {
//                secondaryEx = new SecondaryIframeFailedException(secondaryLocator, ex);
//            }
        }
//        if(!flag){
//            throw new BothIframeLocatorsFailedException(chartName, primaryEx, secondaryEx);
//        }



    }



 */

    // proper in try catch block  : flat try catch approach
    public void switchToChartIframe(String chartName) {
        this.currentChartName = chartName;
        List<String> iframeLocators = locatorReader.getIframeLocators();
        int index = locatorReader.getGraphIndex(chartName);

        if (iframeLocators.isEmpty()) {
            throw new LocatorNotFoundException("No iframe locators found in JSON.");
        }

        String primaryLocator = iframeLocators.get(0).replace("${index}", String.valueOf(index));
        String secondaryLocator = iframeLocators.size() > 1
                ? iframeLocators.get(1).replace("${index}", String.valueOf(index))
                : null;

        Exception primaryEx = null;
        Exception secondaryEx = null;

        // Try Primary
        try {
            WebElement iframe = driver.findElement(By.xpath(primaryLocator));
            driver.switchTo().frame(iframe);
            System.out.println(" Switched to iframe using primary locator: " + primaryLocator);
            return;
        } catch (Exception e) {
            primaryEx = new PrimaryIframeFailedException(primaryLocator, e);
        }

        // Try Secondary
        if (secondaryLocator != null) {
            try {
                WebElement iframe = driver.findElement(By.xpath(secondaryLocator));
                driver.switchTo().frame(iframe);
                System.out.println(" Switched to iframe using secondary locator: " + secondaryLocator);
                return;
            } catch (Exception e) {
                secondaryEx = new SecondaryIframeFailedException(secondaryLocator, e);
            }
        } else {
            secondaryEx = new SecondaryIframeFailedException("Secondary locator not defined", null);
        }

        // Both failed
        throw new BothIframeLocatorsFailedException(chartName, primaryEx, secondaryEx);
    }



    public void clickOnSlice(String chartName, String suffix) {
        List<String> pathLocators = locatorReader.getPathLocators(chartName);

        if (pathLocators == null || pathLocators.isEmpty()) {
            throw new LocatorFormatException("Slice locators missing for chart: " + chartName , "see you");
        }

        boolean clicked = false;
        String dynamicXpath = "";

        for (String template : pathLocators) {
            dynamicXpath = template.replace("${suffix}", suffix);
            try {
                WebElement slice = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
                slice.click();
                System.out.println("Clicked on slice: " + dynamicXpath);
                clicked = true;
                break;
            } catch (TimeoutException e) {
                // try next
            } catch (ElementClickInterceptedException | NoSuchElementException e) {
                // try next
            }
        }

        if (!clicked) {
            throw new TimeoutException("Timeout waiting for slice to be clickable. XPath: " + dynamicXpath);
        }
    }

    public String getPercentageText(String chartName, String percentage) {
        List<String> locators = locatorReader.getPercentageLocators(chartName);

        for (String template : locators) {
            String dynamicXpath = template.replace("${percentage}", percentage);
            try {
                WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));
                return elem.getText().trim();
            } catch (TimeoutException | NoSuchElementException ignored) {
                // Try next
            }
        }

        throw ChartValidationException.percentageNotFound(chartName, percentage);
    }

    public void validateData(String actual, String expected) {
        if (!actual.equals(expected)) {
            throw ChartValidationException.dataMismatch(expected, actual);
        }
        System.out.println(" Data matched: " + expected);
    }

    public String getCurrentChartName() {
        return currentChartName;
    }
}
