package pages.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;

public class ProjectsPage extends HomePage {
    private static final String PROJECT_LIST_ITEM_XPATH_EXPRESSION =
        "//div[@id='content-inner']//td/a[text()='%s']";
    private static final String DELETE_PROJECT_LIST_ITEM_ICON_XPATH_EXPRESSION =
        PROJECT_LIST_ITEM_XPATH_EXPRESSION
        + "/parent::td/following-sibling::td//div[@class='icon-small-delete']";
    private static final String EDIT_PROJECT_LIST_ITEM_ICON_XPATH_EXPRESSION =
        PROJECT_LIST_ITEM_XPATH_EXPRESSION
            + "/parent::td/following-sibling::td//div[@class='icon-small-edit']";
    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            getPageHeaderLocator("Projects")));
    }

    public boolean isProjectListItemPresent(String projectName) {
        return !driver.findElements(
            By.xpath(String.format(PROJECT_LIST_ITEM_XPATH_EXPRESSION, projectName))).isEmpty();
    }

    public void clickDeleteProject(String projectName) {
        By deleteIconLocator = By.xpath(
            String.format(DELETE_PROJECT_LIST_ITEM_ICON_XPATH_EXPRESSION, projectName));
        waitForElementToBeClickable(deleteIconLocator).click();
    }

    public void clickEditProject(String projectName) {
        By editIconLocator = By.xpath(
            String.format(EDIT_PROJECT_LIST_ITEM_ICON_XPATH_EXPRESSION, projectName));
        waitForElementToBeClickable(editIconLocator)
        .click();
    }
}
