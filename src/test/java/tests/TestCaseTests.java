package tests;

import data_providers.TestCaseDataProvider;
import listeners.Retry;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import steps.ProjectDetailsSteps;
import steps.TestCasesSteps;
import utils.ApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.DashboardSteps;

@Log4j2
public class TestCaseTests extends BaseTest {
    private DashboardSteps dashboardSteps;
    private TestCasesSteps testCasesSteps;
    private ProjectDetailsSteps projectDetailsSteps;
    @BeforeClass(alwaysRun = true)
    public void initialize() {
        dashboardSteps = new DashboardSteps(driver);
        testCasesSteps = new TestCasesSteps(driver);
        projectDetailsSteps = new ProjectDetailsSteps(driver);
    }

    @BeforeSuite(alwaysRun = true)
    public void deleteProjectIfExists(){
        ApiUtils.deleteProjectsIfExists(PROJECT_NAME);
    }

    @BeforeClass(alwaysRun = true)
    public void AddProjectIfNotExists() {
        ApiUtils.addProjectIfNotExists(PROJECT_NAME);
    }

    @Test(dataProvider = "textTemplateTestCaseDataProvider",
        dataProviderClass = TestCaseDataProvider.class,
        retryAnalyzer = Retry.class,
        groups = {"all", "test_case"},
        description = "Add a test case using Text template from Project Details page. " +
            "Verify that correct message about successful adding is shown. " +
            "Verify that correct data is shown on the Test Case Details page.")
    public void AddTestCaseBasedOnTextTemplateTest(TestCase inputTestCase) {
        dashboardSteps.openProject(PROJECT_NAME);
        projectDetailsSteps.addTestCaseViaSideBar(inputTestCase);
    }

    @Test(dataProvider = "stepsTemplateTestCaseDataProvider",
        dataProviderClass = TestCaseDataProvider.class,
        retryAnalyzer = Retry.class,
        groups = {"all", "test_case"},
        description = "Add a test case using Steps template from Project Details page. " +
            "Verify that correct message about successful adding is shown. " +
            "Verify that correct data is shown on the Test Case Details page.")
    public void AddTestCaseBasedOnStepsTemplateTest(TestCase inputTestCase) {
        dashboardSteps.openProject(PROJECT_NAME);
        projectDetailsSteps.addTestCaseViaSideBar(inputTestCase);
    }

    @Test(dataProvider = "exploratorySessionTemplateTestCaseDataProvider",
        dataProviderClass = TestCaseDataProvider.class,
        retryAnalyzer = Retry.class,
        groups = {"all", "test_case"},
        description = "Add a test case using Exploratory Session template from Project Details page. " +
            "Verify that correct message about successful adding is shown. " +
            "Verify that correct data is shown on the Test Case Details page.")
    public void AddTestCaseBasedOnExploratorySessionTemplateTest(TestCase inputTestCase) {
        dashboardSteps.openProject(PROJECT_NAME);
        projectDetailsSteps.addTestCaseViaSideBar(inputTestCase);
    }

    @Test(dataProvider = "editTextTemplateTestCaseDataProvider",
        dataProviderClass = TestCaseDataProvider.class,
        retryAnalyzer = Retry.class,
        groups = {"all", "test_case"},
        description = "Edit a text test case from Project Details page. " +
            "Verify that correct message about successful updating is shown. " +
            "Verify that correct data is shown on the Test Case Details page.")
    public void EditTestCaseBasedOnTextTemplateTest(TestCase inputAddTestCase,
                                                   TestCase inputEditTestCase ) {
        dashboardSteps.openProject(PROJECT_NAME);
        projectDetailsSteps.addTestCaseViaSideBar(inputAddTestCase);
        projectDetailsSteps.openTestCasesPage();
        testCasesSteps.editTestCase(inputAddTestCase.getTitle(), inputEditTestCase);
    }

    @Test(dataProvider = "exploratorySessionTemplateTestCaseDataProvider",
        dataProviderClass = TestCaseDataProvider.class,
        retryAnalyzer = Retry.class,
        groups = {"all", "test_case"},
        description = "Delete a test case. Select Mark as Deleted in the confirmation dialog. " +
            "Verify that deleted test cases disappeared from the test cases list.")
    public void deleteTestCase(TestCase inputAddTestCase) {
        dashboardSteps.openProject(PROJECT_NAME);
        projectDetailsSteps.addTestCaseViaSideBar(inputAddTestCase);
        projectDetailsSteps.openTestCasesPage();
        testCasesSteps.deleteTestCase(inputAddTestCase.getTitle());
    }
}
