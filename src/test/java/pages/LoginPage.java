package pages;

import org.openqa.selenium.WebElement;
import utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private static final By LOGIN_BUTTON_LOCATOR = By.cssSelector("#button_primary");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOGIN_BUTTON_LOCATOR));
    }

    public void open() {
        driver.get(PropertyReader.getProperty("test_rail.all.login_url"));
    }

    public void enterEmail(String email) {
        final By EMAIL_LOCATOR = By.cssSelector("#name");
        driver.findElement(EMAIL_LOCATOR).sendKeys(email);
    }

    public void enterPassword(String password) {
        final By PASSWORD_LOCATOR = By.cssSelector("#password");
        driver.findElement(PASSWORD_LOCATOR).sendKeys(password);
    }

    public void clickLogInButton() {
        WebElement element = driver.findElement(LOGIN_BUTTON_LOCATOR);
        waitForElementToBeClickable(element);
        element.click();
    }
}
