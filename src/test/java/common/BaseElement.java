package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseElement {
    protected WebDriver driver;
    protected final WebDriverWait wait;

    public BaseElement(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);

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
}
