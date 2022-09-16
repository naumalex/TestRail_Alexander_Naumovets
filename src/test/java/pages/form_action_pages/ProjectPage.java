package pages.form_action_pages;

import elements.*;
import enums.project.SuiteMode;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.BaseAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;
import pages.form_action_pages.BaseFormActionPage;

public class ProjectPage extends BaseFormActionPage {
    public ProjectPage(WebDriver driver) {
        super(driver);
    }
    
    public void fillForm(Project inputProject) {
        new Input(driver, "name").setText(inputProject.getName());
        new Input(driver, "announcement").setText(inputProject.getAnnouncement());
        new Checkbox(driver, "show_announcement").setValue(inputProject.getIsShowAnnouncement());
        new Radiobutton(driver).selectByEnumValue(inputProject.getSuiteMode());
        new Checkbox(driver, "is_completed").setValue(inputProject.getIsTheProjectCompleted());
    }

    public Project getProjectInfo() {
        String name = new Input(driver, "name").getText();
        String announcement = new Input(driver, "announcement").getText();
        boolean isShowAnnouncement = new Checkbox(driver, "show_announcement").getValue();
        SuiteMode suiteMode = SuiteMode.fromString(new Radiobutton(driver).getSelectedValue());
        boolean isTheProjectCompleted = new Checkbox(driver, "is_completed").getValue();
        return Project.builder()
            .name(name)
            .announcement(announcement)
            .isShowAnnouncement(isShowAnnouncement)
            .suiteMode(suiteMode)
            .isTheProjectCompleted(isTheProjectCompleted)
            .build();
    }
}
