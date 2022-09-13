package pages.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;

public class ProjectDetailsPage extends HomePage {
    private static final String PROJECT_LIST_ITEM_XPATH_EXPRESSION =
        "//div[@id='content-inner']//td/a[text()='%s']";

    public ProjectDetailsPage(WebDriver driver) {
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

}
