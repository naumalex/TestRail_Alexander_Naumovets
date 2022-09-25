package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseElement {
    private static final By BLOCK_UI_LOCATOR = By.cssSelector("div.blockUI.blockOverlay");
    protected WebDriver driver;
    protected final WebDriverWait wait;

    public BaseElement(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);

    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor)driver)
            .executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public boolean isElementPresent(By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean isPresent = !driver.findElements(locator).isEmpty();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return isPresent;
    }

    public void waitUtilBlockUINotDisplayed() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if (!driver.findElements(BLOCK_UI_LOCATOR).isEmpty()) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(BLOCK_UI_LOCATOR));
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public WebElement waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element  = driver.findElement(locator);
        if (!element.isDisplayed()) {
            scrollIntoView(element);
        }
        wait.until(ExpectedConditions.elementToBeClickable(element));
        waitUtilBlockUINotDisplayed();
        return element;
    }
}
