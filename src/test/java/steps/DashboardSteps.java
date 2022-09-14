package steps;

import models.Project;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.project.ProjectDetailsPage;
import pages.project.ProjectPage;
import pages.project.ProjectsPage;

public class DashboardSteps {
    private final DashboardPage dashboardPage;
    private final ProjectDetailsPage projectPage;
    private final ProjectPage addProjectPage;
    private final ProjectsPage projectDetailsPage;

    public DashboardSteps(WebDriver driver) {
        dashboardPage = new DashboardPage(driver);
        projectPage = new ProjectDetailsPage(driver);
        addProjectPage = new ProjectPage(driver);
        projectDetailsPage = new ProjectsPage(driver);
    }

    public void openProject(String projectName) {
        dashboardPage.clickProjectLink(projectName);
        projectPage.waitForPageLoaded();
    }

    public void addProject(Project inputProject) {
        dashboardPage.clickAddProjectButton();
        addProjectPage.waitForPageLoaded();
        addProjectPage.fillForm(inputProject);
        addProjectPage.saveProject();
        Assert.assertEquals(projectDetailsPage.getSaveResultsMessageText(),
            "Successfully added the new project.",
            "Message different from expected is shown after saving a test case");
        Assert.assertTrue(projectDetailsPage
            .isProjectListItemPresent(inputProject.getName()),
            "Added project is not shown in the list of projects");
    }
}
