package pages.project;

import elements.*;
import enums.project.SuiteMode;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;

public class ProjectPage extends HomePage {
    private static final By SUBMIT_BUTTON_LOCATOR = By.cssSelector("#accept");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            SUBMIT_BUTTON_LOCATOR));
    }

    public void fillForm(Project inputProject) {
        new Input(driver, "name").setText(inputProject.getName());
        new Input(driver, "announcement").setText(inputProject.getAnnouncement());
        new Checkbox(driver, "show_announcement").setValue(inputProject.isShowAnnouncement());
        new Radiobutton(driver).selectByEnumValue(inputProject.getSuiteMode());
        new Checkbox(driver, "is_completed").setValue(inputProject.isTheProjectCompleted());
    }

    public Project getProjectInfo() {
        String name = new Input(driver, "name").getText();
        String announcement = new Input(driver, "announcement").getText();
        boolean isShowAnnouncement = new Checkbox(driver, "show_announcement").getValue();
        SuiteMode suiteMode = SuiteMode.fromString(new Radiobutton(driver).getSelectedValue());
        boolean isTheProjectCompleted = new Checkbox(driver, "show_announcement").getValue();
        return Project.builder()
            .name(name)
            .announcement(announcement)
            .isShowAnnouncement(isShowAnnouncement)
            .suiteMode(suiteMode)
            .isTheProjectCompleted(isTheProjectCompleted)
            .build();
    }

    public void saveProject() {
        WebElement addTestCaseButton = driver.findElement(SUBMIT_BUTTON_LOCATOR);
        scrollIntoView(addTestCaseButton);
        addTestCaseButton.click();
    }
}
