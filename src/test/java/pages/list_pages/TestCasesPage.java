package pages.list_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCasesPage extends BaseItemsPage {

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            getPageHeaderLocator("Test Cases")));
    }
}
