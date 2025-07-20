package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import pages.ChartPage;

import static hooks.DriverFactory.getDriver;

public class ChartSteps {

    private ChartPage chartPage;

    public ChartSteps() {
        this.chartPage = new ChartPage(getDriver());
    }

    @Step("Open AnyChart Playground page")
    @Given("I am on the Playground AnyChart page")
    public void openAnyChartPage() {
        getDriver().get("https://playground.anychart.com/");
    }

    @Step("Switch to iframe for chart: {chartName}")
    @When("I switch to {string}")
    public void switchToIframe(String chartName) {
        chartPage.switchToChartIframe(chartName);
    }

    @Step("Click slice with suffix: {suffix}")
    @When("I click slice with suffix {string}")
    public void clickSlice(String suffix) {
        chartPage.clickOnSlice(chartPage.getCurrentChartName(), suffix);
    }

    @Step("Validate chart percentage: {percentage}")
    @Then("I should see percentage {string} in chart")
    public void validatePercentage(String percentage) {
        String actual = chartPage.getPercentageText(chartPage.getCurrentChartName(), percentage);
        chartPage.validateData(actual, percentage);
    }
}
