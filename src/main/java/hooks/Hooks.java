package hooks;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import hooks.DriverFactory;

public class Hooks {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://playground.anychart.com/");
        System.out.println("Browser launched & navigated to Playground.");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            System.out.println(" Scenario failed! Screenshot taken.");
        }

        if (driver != null) {
            driver.quit();
            DriverFactory.resetDriver();
            System.out.println(" Browser closed.");
        }
    }
}
