package tests;

import data_providers.ProjectDataProvider;
import listeners.Retry;
import models.Project;
import org.testng.annotations.*;
import steps.DashboardSteps;
import steps.ProjectsSteps;
import utils.ApiUtils;
import utils.Utils;

public class ProjectTests extends BaseTest {

    private DashboardSteps dashboardSteps;
    private ProjectsSteps projectsSteps;
    private String projectName;

    @BeforeClass(alwaysRun = true)
    public void initialize() {
        dashboardSteps = new DashboardSteps(driver);
        projectsSteps = new ProjectsSteps(driver);
    }

    @Test(dataProvider = "addProjectDataProvider",
        dataProviderClass = ProjectDataProvider.class,
        retryAnalyzer = Retry.class,
    groups = {"all", "project", "needToDeleteAddedData"},
    description = "Add project using Add Project button on the Dashboard page. " +
        "Verify that correct message about successful adding is shown. " +
        "Verify that added project is shown on the Projects page")

    public void AddProject(Project inputProject) {
        ApiUtils.addProjectIfNotExists("To avoid first project specific behaviour");
        dashboardSteps.addProject(inputProject);
        projectName = inputProject.getName();//to delete the added project in AfterMethod
    }

    @Test (retryAnalyzer = Retry.class,
        groups = {"all", "project", "needToAddData"}, description = "Delete project. " +
        "Verify that correct message about successful deleting is shown ." +
        "Verify that the project disappeared from the Projects page.")
    public void deleteProject() {
        projectsSteps.deleteProject(projectName);
    }

    @Test(retryAnalyzer = Retry.class,
        dataProvider = "editProjectDataProvider",
        dataProviderClass = ProjectDataProvider.class,
        groups = {"all", "project", "needToDeleteAddedData", "needToAddData"},
        description = "Edit a project. Verify that correct message about successful update is shown. " +
            "Verify that name of the modified project has been updated in the Projects list. " +
            "Open the project window again. " +
            "Verify that entered in the previous step data was correctly saved.")
    public void alterProject(Project inputProject) {
        projectsSteps.editProject(projectName, inputProject);
    }

    @BeforeMethod(alwaysRun = true, onlyForGroups = "needToAddData")
    public void ensureUniqueProject() {
        projectName = PROJECT_NAME + Utils.getDateTime();
        ApiUtils.addProjectIfNotExists(projectName);
        dashboardSteps.reloadPage();
    }

    @AfterMethod(alwaysRun = true, onlyForGroups = "needToDeleteAddedData")
    public void deleteAddedTestData() {
        ApiUtils.deleteProjectsIfExists(projectName);
    }
}
