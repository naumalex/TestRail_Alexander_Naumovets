package tests;

import data_providers.ProjectDataProvider;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.DashboardSteps;
import steps.ProjectsSteps;
import utils.Api;
import utils.PropertyReader;


@Log4j2
public class ProjectTests extends BaseTest {

    private DashboardSteps dashboardSteps;
    private ProjectsSteps projectsSteps;

    @BeforeClass
    public void initialize() {
        dashboardSteps = new DashboardSteps(driver);
        projectsSteps = new ProjectsSteps(driver);
    }

    @Test(dataProvider = "addProjectDataProvider",
        dataProviderClass = ProjectDataProvider.class)
    public void AddProject(Project inputProject) {
        Api.deleteProjectIfExists(inputProject.getName());
        dashboardSteps.addProject(inputProject);
        Api.deleteProjectIfExists(inputProject.getName());
    }

    @Test
    public void deleteProject() {
        String projectName = PropertyReader.getProperty("test_rail.all.project_name");
        Api.addProjectIfNotExists(projectName);
        projectsSteps.deleteProject(projectName);
    }

    @Test(dataProvider = "editProjectDataProvider",
        dataProviderClass = ProjectDataProvider.class)
    public void alterProject(Project inputProject) {
        String projectName = PropertyReader.getProperty("test_rail.all.project_name");
        Api.addProjectIfNotExists(projectName);
        projectsSteps.editProject(inputProject);
    }
}
