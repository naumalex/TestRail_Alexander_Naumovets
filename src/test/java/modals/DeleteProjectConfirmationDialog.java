package modals;

import common.BaseElement;
import elements.Checkbox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DeleteProjectConfirmationDialog extends BaseElement {

    private static final By CONFIRMATION_CHECKBOX = By.cssSelector("#deleteDialog div.checkbox");
    private static final By CONFIRMATION_DIALOG_OK = By.cssSelector("#deleteDialog a.button-ok");

    public DeleteProjectConfirmationDialog(WebDriver driver) {
        super(driver);
    }

    public void waitForDialogOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(CONFIRMATION_CHECKBOX));
    }

    public void checkDeleteConfirmationCheckbox() {
        new Checkbox(driver, CONFIRMATION_CHECKBOX).setValue(true);
    }

    public void clickOkInDeleteConfirmationDialog() {
        wait.until(ExpectedConditions.elementToBeClickable(CONFIRMATION_DIALOG_OK));
        driver.findElement(CONFIRMATION_DIALOG_OK).click();
    }



}
