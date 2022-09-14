package pages.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class ProjectDetailsPage extends BasePage {
    private static final By TEST_CASES_ADD_LINK_ON_SIDEBAR_LOCATOR =
        By.cssSelector("#sidebar-cases-add");

    public ProjectDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
            TEST_CASES_ADD_LINK_ON_SIDEBAR_LOCATOR));
    }

    public void clickAddTestCaseOnSidebar() {
        WebElement addLink = driver.findElement(TEST_CASES_ADD_LINK_ON_SIDEBAR_LOCATOR);
        waitForElementToBeClickable(addLink);
        addLink.click();
    }
}
