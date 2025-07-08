package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pages.ChartPage;

import static hooks.DriverFactory.getDriver;

public class ChartSteps {

    private ChartPage chartPage;

    public ChartSteps() {
        this.chartPage = new ChartPage(getDriver());
    }

    @Given("I am on the Playground AnyChart page")
    public void openAnyChartPage() {
        getDriver().get("https://playground.anychart.com/");
    }

    @When("I switch to {string}")
    public void switchToIframe(String chartName) {
        chartPage.switchToChartIframe(chartName);
    }

    @When("I click slice with suffix {string}")
    public void clickSlice(String suffix) {
        chartPage.clickOnSlice(chartPage.getCurrentChartName(), suffix);
    }

    @Then("I should see percentage {string} in chart")
    public void validatePercentage(String percentage) {
        String actual = chartPage.getPercentageText(chartPage.getCurrentChartName(), percentage);
        chartPage.validateData(actual, percentage);
    }
}
