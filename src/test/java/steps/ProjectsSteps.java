package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import modals.DeleteProjectConfirmationDialog;
import models.Project;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.form_action_pages.ProjectPage;
import pages.list_pages.ProjectsPage;
@Log4j2
public class ProjectsSteps {
    private final DashboardPage dashboardPage;
    private final ProjectPage projectPage;
    private final ProjectsPage projectsPage;
    private final DeleteProjectConfirmationDialog deleteConfirmationDialog;

    public ProjectsSteps(WebDriver driver) {
        dashboardPage = new DashboardPage(driver);
        projectPage = new ProjectPage(driver);
        projectsPage = new ProjectsPage(driver);
        deleteConfirmationDialog = new DeleteProjectConfirmationDialog(driver);
    }

    @Step
    public void deleteProject(String projectName) {
        log.info("Click Administration link");
        dashboardPage.clickAdministrationLink();
        log.info("Select Projects in the side navigation menu");
        dashboardPage.selectItemInSideNavigationMenu("Projects");
        log.info(String.format("Click Delete against project %s", projectName));
        projectsPage.clickDeleteItem(projectName);
        deleteConfirmationDialog.waitForDialogOpened();
        log.info("Check Delete confirmation checkbox");
        deleteConfirmationDialog.checkDeleteConfirmationCheckbox();
        log.info("Close Delete confirmation dialog with Ok");
        deleteConfirmationDialog.clickOkInDeleteConfirmationDialog();
        log.info("Check the message about successful deleting is shown");
        Assert.assertEquals(projectsPage.getSaveResultsMessageText(),
            "Successfully deleted the project.",
            "Message different from expected is shown after delete a project");
        log.info("Check the item disappeared from the list");
        Assert.assertTrue(projectsPage
                .isItemDisappeared(projectName),
            "Deleted project is shown in the list of projects");
    }

    @Step
    public void editProject(String nameOfProjectToEdit, Project inputProject) {
        log.info("Click Administration link");
        dashboardPage.clickAdministrationLink();
        log.info("Select Projects in the side navigation menu");
        dashboardPage.selectItemInSideNavigationMenu("Projects");
        log.info(String.format("Click Edit button against the project %s", nameOfProjectToEdit));
        projectsPage.clickEditItem(nameOfProjectToEdit);
        projectPage.waitForPageLoaded();
        log.info("Fill Edit Project page");
        projectPage.fillForm(inputProject);
        log.info("Save Project");
        projectPage.save();
        log.info("Verify that the message about successful update is shown");
        Assert.assertEquals(projectsPage.getSaveResultsMessageText(),
            "Successfully updated the project.",
            "Message different from expected is shown after edit a project");
        log.info("Verify that the project with the updated name is shown in the list");
        Assert.assertTrue(projectsPage
                .isItemPresent(inputProject.getName()),
            "Project with updated name is not found");
        log.info(String.format("Click Edit button against the project %s", inputProject.getName()));
        projectsPage.clickEditItem(inputProject.getName());
        projectPage.waitForPageLoaded();
        Project actualProject = projectPage.getProjectInfo();
        //There is no the checkbox in the New Project window so cannot set it even with default value
        //but the checkbox exists in the Edit Project window and false, so we change false value to null.
        actualProject.setIsTheProjectCompleted(null);
        log.info("Verify that entered data has been correctly saved");
        Assert.assertEquals(actualProject, inputProject,
            "Not all data have been correctly saved");
        log.info("Click Cancel on the Edit Project page");
        projectPage.clickCancel();
    }
}
