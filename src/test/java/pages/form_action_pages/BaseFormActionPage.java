package pages.form_action_pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;

@Log4j2
public abstract class BaseFormActionPage extends HomePage {

    private static final By PAGE_CAPTION_LOCATOR =
        By.cssSelector(".content-header-title.page_title");
    private static final By SUBMIT_BUTTON_LOCATOR = By.cssSelector("#accept");
    private static final By CANCEL_BUTTON_LOCATOR = By.cssSelector("#admin-integration-form-cancel");

    public BaseFormActionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(PAGE_CAPTION_LOCATOR));

    }

    public void save() {
        waitForElementToBeClickable(SUBMIT_BUTTON_LOCATOR).click();
    }

    public void clickCancel() {
        waitForElementToBeClickable(CANCEL_BUTTON_LOCATOR).click();
    }

}
