package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import modals.DeleteTestCaseConfirmationDialog;
import models.TestCase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.form_action_pages.TestCasePage;
import pages.list_pages.TestCasesPage;
import pages.ProjectDetailsPage;
import pages.TestCaseDetailsPage;

@Log4j2
public class TestCasesSteps {
    private final TestCasePage testCasePage;
    private final TestCasesPage testCasesPage;
    private final TestCaseDetailsPage testCaseDetailsPage;
    private final DeleteTestCaseConfirmationDialog deleteTestCaseConfirmationDialog;
    private final ProjectDetailsPage projectDetailsPage;

    public TestCasesSteps(WebDriver driver) {
        testCasePage = new TestCasePage(driver);
        testCasesPage = new TestCasesPage(driver);
        testCaseDetailsPage = new TestCaseDetailsPage(driver);
        projectDetailsPage = new ProjectDetailsPage(driver);
        deleteTestCaseConfirmationDialog = new DeleteTestCaseConfirmationDialog(driver);
    }

    @Step
    public void deleteTestCase(String testCaseName) {
        log.info("Select Test Cases in the top navigation menu");
        projectDetailsPage.selectItemInTopNavigationMenu("Test Cases");
        testCasesPage.waitForPageLoaded();
        log.info(String.format("Click Delete against test case %s", testCaseName));
        testCasesPage.clickDeleteItem(testCaseName);
        deleteTestCaseConfirmationDialog.waitForDialogOpened();
        log.info("Click Mark As Deleted in the confirmation dialog");
        deleteTestCaseConfirmationDialog.clickMarkAsDeleted();
        log.info("Verify that the item has disappeared from the list");
        Assert.assertTrue(testCasesPage
                .isItemDisappeared(testCaseName),
            "Deleted test case is shown in the list of projects");
    }

    @Step
    public void editTestCase(String testCaseToEditTitle, TestCase inputTestCase) {
        log.info("Select Test Cases in the top navigation menu");
        projectDetailsPage.selectItemInTopNavigationMenu("Test Cases");
        testCasesPage.waitForPageLoaded();
        log.info(String.format("Click Edit against test case %s", testCaseToEditTitle));
        testCasesPage.clickEditItem(testCaseToEditTitle);
        testCasePage.waitForPageLoaded();
        log.info("Fill Edit Test Case page");
        testCasePage.fillForm(inputTestCase);
        log.info("Save Test Case");
        testCasePage.save();
        log.info("Verify that message about successful updating is shown");
        Assert.assertEquals(testCasePage.getSaveResultsMessageText(),
            "Successfully updated the test case.",
            "Message different from expected is shown after edit a test case");
        log.info("Verify that entered data was correctly saved");
        Assert.assertEquals(testCaseDetailsPage.getTestCaseInfo(inputTestCase.getTemplate()),
            inputTestCase,
            "Test case has been created with incorrect data");
        log.info("Go back to Project Details page");
        testCaseDetailsPage.openProjectDetails();
        projectDetailsPage.waitForPageLoaded();
        }
}
