package steps;

import modals.DeleteConfirmationDialog;
import models.Project;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.form_action_pages.ProjectPage;
import pages.project.ProjectsPage;

public class ProjectsSteps {
    private final DashboardPage dashboardPage;
    private final ProjectPage projectPage;
    private final ProjectsPage projectsPage;
    private final DeleteConfirmationDialog deleteConfirmationDialog;

    public ProjectsSteps(WebDriver driver) {
        dashboardPage = new DashboardPage(driver);
        projectPage = new ProjectPage(driver);
        projectsPage = new ProjectsPage(driver);
        deleteConfirmationDialog = new DeleteConfirmationDialog(driver);
    }

    public void deleteProject(String projectName) {
        dashboardPage.clickAdministrationLink();
        dashboardPage.selectItemInSideNavigationMenu("Projects");
        projectsPage.clickDeleteProject(projectName);
        deleteConfirmationDialog.waitForDialogOpened();
        deleteConfirmationDialog.checkDeleteConfirmationCheckbox();
        deleteConfirmationDialog.clickOkInDeleteConfirmationDialog();
        Assert.assertEquals(projectsPage.getSaveResultsMessageText(),
            "Successfully deleted the project.",
            "Message different from expected is shown after delete a project");
        Assert.assertFalse(projectsPage
                .isProjectListItemPresent(projectName),
            "Deleted project is shown in the list of projects");
    }

    public void editProject(Project inputProject) {
        dashboardPage.clickAdministrationLink();
        dashboardPage.selectItemInSideNavigationMenu("Projects");
        projectsPage.clickEditProject(inputProject.getName());
        projectPage.waitForPageLoaded();
        projectPage.fillForm(inputProject);
        projectPage.save();

        Assert.assertEquals(projectsPage.getSaveResultsMessageText(),
            "Successfully updated the project.",
            "Message different from expected is shown after edit a project");
        Assert.assertTrue(projectsPage
                .isProjectListItemPresent(inputProject.getName()),
            "Project with updated name is not found");
        projectsPage.clickEditProject(inputProject.getName());
        projectPage.waitForPageLoaded();
        Assert.assertEquals(projectPage.getProjectInfo(), inputProject,
            "Not all data have been correctly saved");
        projectPage.save();
    }
}
