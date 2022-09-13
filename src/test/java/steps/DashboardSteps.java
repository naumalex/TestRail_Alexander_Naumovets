package steps;

import models.Project;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.ProjectPage;
import pages.project.AddProjectPage;
import pages.project.ProjectDetailsPage;

public class DashboardSteps {
    private final DashboardPage dashboardPage;
    private final ProjectPage projectPage;
    private final AddProjectPage addProjectPage;
    private final ProjectDetailsPage projectDetailsPage;

    public DashboardSteps(WebDriver driver) {
        dashboardPage = new DashboardPage(driver);
        projectPage = new ProjectPage(driver);
        addProjectPage = new AddProjectPage(driver);
        projectDetailsPage = new ProjectDetailsPage(driver);
    }

    public void openProject(String projectName) {
        dashboardPage.clickProjectLink(projectName);
        projectPage.waitForPageLoaded();
    }

    public void addProject(Project inputProject) {
        dashboardPage.clickAddProjectButton();
        addProjectPage.waitForPageLoaded();
        addProjectPage.fillForm(inputProject);
        addProjectPage.clickAddProjectButton();
        Assert.assertEquals(projectDetailsPage.getSaveResultsMessageText(),
            "Successfully added the new project.",
            "Message different from expected is shown after saving a test case");
        Assert.assertTrue(projectDetailsPage
            .isProjectListItemPresent(inputProject.getName()),
            "Added project is not shown in the list of projects");
    }
}
