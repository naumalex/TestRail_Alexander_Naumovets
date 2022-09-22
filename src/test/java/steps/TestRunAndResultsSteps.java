package steps;

import enums.test_result.TestResult;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import modals.AddResultDialog;
import models.TestCase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.TestRunAndResultsPage;

import java.util.List;

@Log4j2
public class TestRunAndResultsSteps {
    private final TestRunAndResultsPage testRunAndResultsPage;
    private final AddResultDialog addResultDialog;

    public TestRunAndResultsSteps(WebDriver driver) {
        testRunAndResultsPage = new TestRunAndResultsPage(driver);
        addResultDialog = new AddResultDialog(driver);
    }

    @Step
    public void verifyListOfTestCasesIncludedInTestRun(List<TestCase> expectedList) {
        log.info("Get list of test case included in Test run");
        List<String> expectedListOfTestcaseTitles = expectedList.stream()
            .map(TestCase::getTitle).toList();
        log.info("Verify that all the test cases were included in Test run");
        Assert.assertEquals(testRunAndResultsPage.getListOfTestCaseTitles(),
            expectedListOfTestcaseTitles,
            "List of test cases included in test run is incorrect");
    }

    @Step
    public void addTestResult(String testCaseTitle, TestResult testResult) {
        log.info(String.format("Select %s test result for test case %s",
            testResult.getName(), testCaseTitle));
        testRunAndResultsPage.selectStatusInTestResultDropdown(testCaseTitle, testResult);
        addResultDialog.waitForDialogOpened();
        log.info("Click Add result in the Add Test result Dialog");
        addResultDialog.clickAddResult();
        log.info("Verify that test results summary has been correctly updated");
        Assert.assertTrue(testRunAndResultsPage.isTextExistInTheSummaryChart(testResult),
            String.format("Count in Test Results Summary is incorrect. " +
                "Expected value is 1 in %s category.", testResult.getName()));
    }
}
