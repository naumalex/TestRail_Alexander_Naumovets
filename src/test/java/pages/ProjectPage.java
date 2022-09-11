package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectPage extends BasePage {
    private static final By TEST_CASES_ADD_LINK_ON_SIDEBAR_LOCATOR =
        By.cssSelector("#sidebar-cases-add");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
            TEST_CASES_ADD_LINK_ON_SIDEBAR_LOCATOR));
    }

    public void clickAddTestCaseOnSidebar() {
        WebElement addLink = driver.findElement(TEST_CASES_ADD_LINK_ON_SIDEBAR_LOCATOR);
        scrollIntoView(addLink);
        wait.until(ExpectedConditions.elementToBeClickable(addLink));
        addLink.click();
    }


}
