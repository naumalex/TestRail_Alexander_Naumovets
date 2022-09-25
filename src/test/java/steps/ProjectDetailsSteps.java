package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import models.TestRun;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.TestRunAndResultsPage;
import pages.form_action_pages.TestCasePage;
import pages.form_action_pages.TestRunPage;
import pages.list_pages.TestCasesPage;
import pages.ProjectDetailsPage;
import pages.TestCaseDetailsPage;

import java.util.List;

@Log4j2
public class ProjectDetailsSteps {
    private final ProjectDetailsPage projectDetailsPage;
    private final TestCasePage testCasePage;
    private final TestCasesPage testCasesPage;
    private final TestCaseDetailsPage testCaseDetailsPage;
    private final TestRunPage testRunPage;
    private final TestRunAndResultsPage testRunAndResultsPage;


    public ProjectDetailsSteps(WebDriver driver) {
        projectDetailsPage = new ProjectDetailsPage(driver);
        testCasePage = new TestCasePage(driver);
        testCasesPage = new TestCasesPage(driver);
        testCaseDetailsPage = new TestCaseDetailsPage(driver);
        testRunPage = new TestRunPage(driver);
        testRunAndResultsPage = new TestRunAndResultsPage(driver);
    }

    @Step
    public void addTestCaseViaSideBar(TestCase inputTestCase) {
        log.info("Click Add Test Case link on sidebar");
        projectDetailsPage.clickAddTestCaseOnSidebar();
        testCasePage.waitForPageLoaded();
        log.info("Fill in New Test Case page");
        testCasePage.fillForm(inputTestCase);
        log.info("Save Test case");
        testCasePage.save();
        testCaseDetailsPage.waitForPageLoaded();
        log.info("Verify that the message about successful adding is shown");
        Assert.assertEquals(testCaseDetailsPage.getSaveResultsMessageText(),
            "Successfully added the new test case. Add another",
            "Message different from expected is shown after saving a test case");
        log.info("Verify that the test case data was correctly stored");
        Assert.assertEquals(testCaseDetailsPage.getTestCaseInfo(inputTestCase.getTemplate()),
            inputTestCase,
            "Test case has been created with incorrect data");
        log.info("Return to Project Details");
        testCaseDetailsPage.openProjectDetails();
        projectDetailsPage.waitForPageLoaded();
    }

    public void addTestCasesViaSideBar(List<TestCase> testCases) {
        testCases.stream().forEach(this::addTestCaseViaSideBar);
    }
    @Step
    public void addTestRunViaSideBar(TestRun inputTestRun) {
        log.info("Click Add Test Case link on sidebar");
        projectDetailsPage.clickAddTestRunOnSidebar();
        testRunPage.waitForPageLoaded();
        log.info("Fill in the new test run page");
        testRunPage.fillForm(inputTestRun);
        log.info("Save Test run");
        testRunPage.save();
        testRunAndResultsPage.waitForPageLoaded();
        log.info("Verify that the message about successful creating of the test run is shown");
        Assert.assertEquals(testRunAndResultsPage.getSaveResultsMessageText(),
            "Successfully added the new test run.",
            "Message different from expected is shown after saving a test run");
        testRunAndResultsPage.waitForPageLoaded();
    }

    @Step
    public void openTestCasesPage() {
        log.info("Select Test Cases in the top navigation menu");
        projectDetailsPage.selectItemInTopNavigationMenu("Test Cases");
        testCasesPage.waitForPageLoaded();
    }
}
