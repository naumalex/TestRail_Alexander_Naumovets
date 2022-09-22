package modals;

import common.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddResultDialog extends BaseElement {

    private static final By ADD_RESULT_BUTTON = By.cssSelector("#addResultSubmit");

    public AddResultDialog(WebDriver driver) {
        super(driver);
    }

    public void waitForDialogOpened() {
        waitForElementToBeClickable(ADD_RESULT_BUTTON);
    }

    public void clickAddResult() {
        waitForElementToBeClickable(ADD_RESULT_BUTTON).click();
    }
}
