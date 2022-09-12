package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends  BasePage {

    private static final By PROJECTS_PAGE_HEADER_LOCATOR =
        By.cssSelector("#content-header .page_title");
    private static final String PROJECT_ITEM_XPATH_EXPRESSION =
        "//div[@id='content_container']//a[text() = '%s']";

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
        wait.until(ExpectedConditions.elementToBeClickable(projectItemLocator));
        driver.findElement(projectItemLocator).click();
    }

}
