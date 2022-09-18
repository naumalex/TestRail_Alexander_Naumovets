package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectDetailsPage extends HomePage {
    private static final By TEST_CASES_ADD_LINK_ON_SIDEBAR_LOCATOR =
        By.cssSelector("#sidebar-cases-add");
    private static final By TEST_RUN_ADD_LINK_ON_SIDEBAR_LOCATOR =
        By.cssSelector("#sidebar-runs-add");

    public ProjectDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
            TEST_CASES_ADD_LINK_ON_SIDEBAR_LOCATOR));
    }

    public void clickAddTestCaseOnSidebar() {
        waitForElementToBeClickable(TEST_CASES_ADD_LINK_ON_SIDEBAR_LOCATOR)
        .click();
    }

    public void clickAddTestRunOnSidebar() {
        waitForElementToBeClickable(TEST_RUN_ADD_LINK_ON_SIDEBAR_LOCATOR)
            .click();
    }

}
