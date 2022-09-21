package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.ProjectDetailsPage;
import pages.form_action_pages.ProjectPage;
import pages.list_pages.ProjectsPage;

@Log4j2
public class DashboardSteps extends BaseStep {
    private final DashboardPage dashboardPage;
    private final ProjectDetailsPage projectDetailsPage;
    private final ProjectPage projectPage;
    private final ProjectsPage projectsPage;

    public DashboardSteps(WebDriver driver) {
        super(driver);
        projectDetailsPage = new ProjectDetailsPage(driver);
        dashboardPage = new DashboardPage(driver);
        projectPage = new ProjectPage(driver);
        projectsPage = new ProjectsPage(driver);
    }

    @Step
    public void openProject(String projectName) {
        log.info(String.format("Click %s  link on the dashboard page to open the project", projectName));
        dashboardPage.clickProjectLink(projectName);
        projectDetailsPage.waitForPageLoaded();
    }
    @Step
    public void addProject(Project inputProject) {
        log.info("Click Add Project button on the Dashboard page");
        dashboardPage.clickAddProjectButton();
        projectPage.waitForPageLoaded();
        log.info("Fill data in New Project page");
        projectPage.fillForm(inputProject);
        log.info("Save project");
        projectPage.save();
        log.info("Verify that correct message about successful project adding is shown");
        Assert.assertEquals(projectsPage.getSaveResultsMessageText(),
            "Successfully added the new project.",
            "Message different from expected is shown after saving a test case");
        log.info(String.format("Verify that the project %s added to the Projects list.",
            inputProject.getName()));
        Assert.assertTrue(projectsPage
            .isItemPresent(inputProject.getName()),
            "Added project is not shown in the list of projects");
    }
}
