package steps;

import models.TestCase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.form_action_pages.ProjectPage;
import pages.project.ProjectDetailsPage;
import pages.form_action_pages.TestCasePage;
import pages.test_case.TestCaseDetailsPage;

public class TestCaseSteps {
    private final ProjectDetailsPage projectDetailsPage;
    private final TestCasePage testCasePage;
    private final TestCaseDetailsPage testCaseDetailsPage;

    public TestCaseSteps(WebDriver driver) {
        projectDetailsPage = new ProjectDetailsPage(driver);
        testCasePage = new TestCasePage(driver);
        testCaseDetailsPage = new TestCaseDetailsPage(driver);
    }

    public void addTestCaseViaSideBar(TestCase inputTestCase) {
        projectDetailsPage.clickAddTestCaseOnSidebar();
        testCasePage.waitForPageLoaded();
        testCasePage.fillForm(inputTestCase);
        testCasePage.save();
        testCaseDetailsPage.waitForPageLoaded();
        Assert.assertEquals(testCaseDetailsPage.getSaveResultsMessageText(),
            "Successfully added the new test case. Add another",
            "Message different from expected is shown after saving a test case");
        Assert.assertEquals(testCaseDetailsPage.getTestCaseInfo(inputTestCase.getTemplate()),
            inputTestCase,
            "Test case has been created with incorrect data");
    }
}
