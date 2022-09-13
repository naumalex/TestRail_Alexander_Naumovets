package pages.project;

import elements.*;
import enums.project.SuiteMode;
import enums.test_case.Template;
import models.Project;
import models.TestStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;

import java.util.List;

public class AddProjectPage extends HomePage {
    private static final By ADD_TEST_CASE_BUTTON_LOCATOR = By.cssSelector("#accept");

    public AddProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            getPageHeaderLocator("Add Project")));
    }

    public void fillForm(Project inputProject) {
        new Input(driver, "name").setText(inputProject.getName());
        new Input(driver, "announcement").setText(inputProject.getAnnouncement());
        new Checkbox(driver, "show_announcement").setValue(inputProject.isShowAnnouncement());
        new Radiobutton(driver).selectByEnumValue(inputProject.getSuiteMode());
    }

    public void clickAddProjectButton() {
        WebElement addTestCaseButton = driver.findElement(ADD_TEST_CASE_BUTTON_LOCATOR);
        scrollIntoView(addTestCaseButton);
        addTestCaseButton.click();
    }
}
