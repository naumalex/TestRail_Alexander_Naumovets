package steps;

import models.Project;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.project.ProjectDetailsPage;
import pages.form_action_pages.ProjectPage;
import pages.project.ProjectsPage;

public class DashboardSteps {
    private final DashboardPage dashboardPage;
    private final ProjectDetailsPage projectDetailsPage;
    private final ProjectPage projectPage;
    private final ProjectsPage projectsPage;

    public DashboardSteps(WebDriver driver) {
        dashboardPage = new DashboardPage(driver);
        projectDetailsPage = new ProjectDetailsPage(driver);
        projectPage = new ProjectPage(driver);
        projectsPage = new ProjectsPage(driver);
    }

    public void openProject(String projectName) {
        dashboardPage.clickProjectLink(projectName);
        projectPage.waitForPageLoaded();
    }

    public void addProject(Project inputProject) {
        dashboardPage.clickAddProjectButton();
        projectPage.waitForPageLoaded();
        projectPage.fillForm(inputProject);
        projectPage.save();
        Assert.assertEquals(projectsPage.getSaveResultsMessageText(),
            "Successfully added the new project.",
            "Message different from expected is shown after saving a test case");
        Assert.assertTrue(projectsPage
            .isProjectListItemPresent(inputProject.getName()),
            "Added project is not shown in the list of projects");
    }
}
