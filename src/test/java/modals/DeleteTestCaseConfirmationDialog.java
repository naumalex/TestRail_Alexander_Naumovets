package modals;

import common.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DeleteTestCaseConfirmationDialog extends BaseElement {

    private static final By CONFIRMATION_DIALOG_OK = By.xpath(
        "//div[@id ='dialog-ident-casesDeletionDialog']" +
            "//a[contains(text(),'Mark as Deleted')]");

    public DeleteTestCaseConfirmationDialog(WebDriver driver) {
        super(driver);
    }

    public void waitForDialogOpened() {
        wait.until(ExpectedConditions.elementToBeClickable(CONFIRMATION_DIALOG_OK));
    }

    public void clickMarkAsDeleted() {
        wait.until(ExpectedConditions.elementToBeClickable(CONFIRMATION_DIALOG_OK));
        driver.findElement(CONFIRMATION_DIALOG_OK).click();
    }
}
