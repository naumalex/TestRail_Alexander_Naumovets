package tests;

import data_providers.TestRunDataProvider;
import enums.test_result.TestResult;
import listeners.Retry;
import models.TestCase;
import models.TestRun;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.DashboardSteps;
import steps.ProjectDetailsSteps;
import steps.TestRunAndResultsSteps;
import utils.ApiUtils;
import utils.PropertyReader;
import utils.Utils;

import java.util.List;
public class TestRunTests extends BaseTest {
    private DashboardSteps dashboardSteps;
    private ProjectDetailsSteps projectDetailsSteps;
    private TestRunAndResultsSteps testRunAndResultsSteps;
    private String projectName;

    @BeforeClass(alwaysRun = true)
    public void initialize() {
        dashboardSteps = new DashboardSteps(driver);
        projectDetailsSteps = new ProjectDetailsSteps(driver);
        testRunAndResultsSteps = new TestRunAndResultsSteps(driver);
    }

    @Test(dataProvider = "testRunDataProvider", dataProviderClass = TestRunDataProvider.class,
        retryAnalyzer = Retry.class,
        groups = {"all", "test_run"},
        description = "Add 3 test cases to a project. Add a test run for the project from the" +
            " Project Details page. " +
            "Verify that the message about successful test run is shown." +
            "Verify that all the 3 added test cases are included in the test run.")
    public void addTestRunTest(List<TestCase> testCases, TestRun inputTestRun) {
        dashboardSteps.openProject(projectName);
        projectDetailsSteps.addTestCasesViaSideBar(testCases);
        projectDetailsSteps.addTestRunViaSideBar(inputTestRun);
        testRunAndResultsSteps.verifyListOfTestCasesIncludedInTestRun(testCases);
    }

    @Test(dataProvider = "testRunDataProvider", dataProviderClass = TestRunDataProvider.class,
        retryAnalyzer = Retry.class,
        groups = {"all", "test_run"},
        description = "Add 3 test cases to a project. Add a test run for the project from the" +
            " Project Details page. Mark the first test case as Passed." +
            "Verify that summary panek shows the updated value for number of passed test cases.")
    public void addTestResultToTestCasesInTestRun(List<TestCase> testCases, TestRun inputTestRun) {
        dashboardSteps.openProject(projectName);
        projectDetailsSteps.addTestCasesViaSideBar(testCases);
        projectDetailsSteps.addTestRunViaSideBar(inputTestRun);
        testRunAndResultsSteps.addTestResult(testCases.get(0).getTitle(), TestResult.PASSED);
    }
   @BeforeMethod(alwaysRun = true)
    public void ensureUniqueProjectWithMilestone() {
       projectName = PROJECT_NAME + Utils.getDateTime();
       ApiUtils.addProjectIfNotExists(projectName);
       ApiUtils.addMilestoneIfNotExist(projectName,
           PropertyReader.getProperty("test_rail.test_run.milestone"));
       dashboardSteps.reloadPage();
   }

   @AfterMethod(alwaysRun = true)
   public void deleteAddedTestData() {
        ApiUtils.deleteProjectsIfExists(projectName);
    }



}
