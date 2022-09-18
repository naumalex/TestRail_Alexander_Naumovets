package tests;

import data_providers.ProjectDataProvider;
import listeners.Retry;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.DashboardSteps;
import steps.ProjectsSteps;
import utils.ApiUtils;
import utils.PropertyReader;
import utils.Utils;

@Log4j2
public class ProjectTests extends BaseTest {

    private DashboardSteps dashboardSteps;
    private ProjectsSteps projectsSteps;

    @BeforeClass(alwaysRun = true)
    public void initialize() {
        dashboardSteps = new DashboardSteps(driver);
        projectsSteps = new ProjectsSteps(driver);
    }

    @Test(dataProvider = "addProjectDataProvider",
        dataProviderClass = ProjectDataProvider.class,
        retryAnalyzer = Retry.class,
    groups = {"all", "project"},
    description = "Add project using Add Project button on the Dashboard page. " +
        "Verify that correct message about successful adding is shown. " +
        "Verify that added project is shown on the Projects page")

    public void AddProject(Project inputProject) {
        ApiUtils.addProjectIfNotExists("To avoid first project specific behaviour");
        dashboardSteps.addProject(inputProject);
    }

    @Test (retryAnalyzer = Retry.class,
        groups = {"all", "project"}, description = "Delete project. " +
        "verify that correct message about successful deleting is shown ." +
        "Verify that the project disappeared from the Projects page.")
    public void deleteProject() {
        String projectName = PropertyReader.getProperty("test_rail.all.project_name")
            + Utils.getDateTime();
        ApiUtils.addProjectIfNotExists(projectName);
        projectsSteps.deleteProject(projectName);
    }

    @Test(retryAnalyzer = Retry.class,
        dataProvider = "editProjectDataProvider",
        dataProviderClass = ProjectDataProvider.class,
        groups = {"all", "project"},
        description = "Edit a project. Verify that correct message about successful update is shown. " +
            "Verify that name of the modified project has been updated in the Projects list. " +
            "Open the project window again. " +
            "Verify that entered in the previous step data was correctly saved.  ")
    public void alterProject(Project inputProject) {
        String projectName = PropertyReader.getProperty("test_rail.all.project_name")
            + Utils.getDateTime();
        ApiUtils.addProjectIfNotExists(projectName);
        projectsSteps.editProject(projectName, inputProject);
    }
}
