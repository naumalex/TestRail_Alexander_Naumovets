package steps;

import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.ProjectPage;

public class DashboardSteps {
    private final DashboardPage dashboardPage;
    private final ProjectPage projectPage;

    public DashboardSteps(WebDriver driver) {
        dashboardPage = new DashboardPage(driver);
        projectPage = new ProjectPage(driver);
    }

    public void openProject(String projectName) {
        dashboardPage.clickProjectLink(projectName);
        projectPage.waitForPageLoaded();
    }
}
