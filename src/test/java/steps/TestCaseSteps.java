package steps;

import models.TestCase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.project.ProjectDetailsPage;
import pages.test_case.AddTestCasePage;
import pages.test_case.TestCaseDetailsPage;

public class TestCaseSteps {
    private final ProjectDetailsPage projectPage;
    private final AddTestCasePage addTestCasePage;
    private final TestCaseDetailsPage testCaseDetailsPage;

    public TestCaseSteps(WebDriver driver) {
        projectPage = new ProjectDetailsPage(driver);
        addTestCasePage = new AddTestCasePage(driver);
        testCaseDetailsPage = new TestCaseDetailsPage(driver);
    }

    public void addTestCaseViaSideBar(TestCase inputTestCase) {
        projectPage.clickAddTestCaseOnSidebar();
        addTestCasePage.waitForPageLoaded();
        addTestCasePage.fillForm(inputTestCase);
        addTestCasePage.clickAddTestCaseButton();
        testCaseDetailsPage.waitForPageLoaded();
        Assert.assertEquals(testCaseDetailsPage.getSaveResultsMessageText(),
            "Successfully added the new test case. Add another",
            "Message different from expected is shown after saving a test case");
        Assert.assertEquals(testCaseDetailsPage.getTestCaseInfo(inputTestCase.getTemplate()),
            inputTestCase,
            "Test case has been created with incorrect data");
    }
}
