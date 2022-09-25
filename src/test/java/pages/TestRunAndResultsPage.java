package pages;

import elements.dropdown_list.TestResultDropDownList;
import enums.test_result.TestResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TestRunAndResultsPage extends HomePage {
    private static final By TEST_CASES_LIST_TITLE_LOCATOR = By.xpath(
        "//tr[starts-with(@id, 'row-')]//td[3]");

    private static final String TEST_RESULTS_COUNT_LOCATOR =
        "//div[@id='legend-']//div[text()='1 %s']";

    public TestRunAndResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            getPageHeaderLocator("Test Run")));
        wait.until(p -> !getListOfTestCaseTitles().isEmpty());
    }

    public List<String> getListOfTestCaseTitles() {
        return driver.findElements(TEST_CASES_LIST_TITLE_LOCATOR).stream()
            .map(p ->p.getAttribute("innerText")).toList();
    }

    public void selectStatusInTestResultDropdown(String testCaseTitle, TestResult testResult) {
        new TestResultDropDownList(driver, testCaseTitle).selectByEnumValue(testResult);
    }

    public boolean isTextExistInTheSummaryChart(TestResult status) {
        return !driver.findElements(By.xpath(
            String.format(TEST_RESULTS_COUNT_LOCATOR, status.getName()))).isEmpty();
    }
}
