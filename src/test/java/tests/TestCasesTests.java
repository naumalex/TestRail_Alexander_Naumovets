package tests;

import data_providers.TestCaseDataProvider;
import models.TestCase;
import steps.TestCaseSteps;
import utils.PropertyReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.DashboardSteps;

public class TestCasesTests extends BaseTest {
    public static final String PROJECT_NAME = PropertyReader.getProperty("test_rail.all.project_name");
    private DashboardSteps dashboardSteps;
    private TestCaseSteps testCaseSteps;

    @BeforeClass
    public void initialize() {
        dashboardSteps = new DashboardSteps(driver);
        testCaseSteps = new TestCaseSteps(driver);
    }

    @BeforeMethod
    public void AddProjectIfNotExists() {
        //Add project with name = PROJECT_NAME if it does not exist using API
    }

    @Test(dataProvider = "textTemplateTestCaseDataProvider",
        dataProviderClass = TestCaseDataProvider.class)
    public void AddTestCaseBasedOnTextTemplateTest(TestCase inputTestCase) {
        dashboardSteps.openProject(PROJECT_NAME);
        testCaseSteps.addTestCaseViaSideBar(inputTestCase);
    }

    @Test(dataProvider = "stepsTemplateTestCaseDataProvider",
        dataProviderClass = TestCaseDataProvider.class)
    public void AddTestCaseBasedOnStepsTemplateTest(TestCase inputTestCase) {
        dashboardSteps.openProject(PROJECT_NAME);
        testCaseSteps.addTestCaseViaSideBar(inputTestCase);
    }

    @Test(dataProvider = "exploratorySessionTemplateTestCaseDataProvider",
        dataProviderClass = TestCaseDataProvider.class)
    public void AddTestCaseBasedOnExploratorySessionTemplateTest(TestCase inputTestCase) {
        dashboardSteps.openProject(PROJECT_NAME);
        testCaseSteps.addTestCaseViaSideBar(inputTestCase);
    }




}
