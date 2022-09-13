package tests;

import data_providers.ProjectDataProvider;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.DashboardSteps;
import utils.Api;


@Log4j2
public class ProjectTests extends BaseTest {

    private DashboardSteps dashboardSteps;

    @BeforeClass
    public void initialize() {
        dashboardSteps = new DashboardSteps(driver);
    }

    @Test(dataProvider = "addProjectDataProvider",
        dataProviderClass = ProjectDataProvider.class)
    public void AddProject(Project inputProject) {
        Api.deleteProjectIfExists(inputProject.getName());
        dashboardSteps.addProject(inputProject);
    }
}
