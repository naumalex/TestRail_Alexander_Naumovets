package tests;

import api.ProjectAdaptor;
import com.google.gson.Gson;
import data_providers.TestCaseDataProvider;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.testng.annotations.AfterClass;
import steps.TestCaseSteps;
import utils.Api;
import utils.PropertyReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.DashboardSteps;

@Log4j2
public class TestCaseTests extends BaseTest {
    public static final String PROJECT_NAME = PropertyReader.getProperty("test_rail.all.project_name");
    private DashboardSteps dashboardSteps;
    private TestCaseSteps testCaseSteps;
    private final Gson gson = new Gson();
    private final ProjectAdaptor adaptor = new ProjectAdaptor();
    @BeforeClass
    public void initialize() {
        dashboardSteps = new DashboardSteps(driver);
        testCaseSteps = new TestCaseSteps(driver);
    }

    @BeforeClass
    public void AddProjectIfNotExists() {
        Api.deleteProjectIfExists(PROJECT_NAME);
        Api.addProjectIfNotExists(PROJECT_NAME);
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

    @AfterClass
    public void deleteTestProject() {
        Api.deleteProjectIfExists(PROJECT_NAME);
    }
}
