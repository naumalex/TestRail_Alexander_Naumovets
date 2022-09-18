package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends  HomePage {

    private static final By PROJECTS_PAGE_HEADER_LOCATOR =
        By.cssSelector("#content-header .page_title");
    private static final String PROJECT_ITEM_XPATH_EXPRESSION =
        "//div[@id='content_container']//a[text() = '%s']";
    private static final By ADD_PROJECT_BUTTON_LOCATOR = By.cssSelector("#sidebar-projects-add");
    private static final By TEST_RUN_VIEW_ALL_LINK =
        By.cssSelector("#sidebar-runs-viewall");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            PROJECTS_PAGE_HEADER_LOCATOR));
    }

    public void clickProjectLink(String projectName) {
        By projectItemLocator = By.xpath(
            String.format(PROJECT_ITEM_XPATH_EXPRESSION, projectName));
     //   WebElement element = waitForElementToBeClickable(projectItemLocator);
      //  scrollIntoView(element);
        waitForElementToBeClickable(projectItemLocator).click();
    }

    public void clickAddProjectButton() {
        waitForElementToBeClickable(ADD_PROJECT_BUTTON_LOCATOR).click();
    }

    public void clickTestRunsViewAllLink() {
        waitForElementToBeClickable(TEST_RUN_VIEW_ALL_LINK).click();
    }
}
