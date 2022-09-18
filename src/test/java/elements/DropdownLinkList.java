package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DropdownLinkList extends BaseInputElement {


    private static final String DROPDOWN_ITEM_XPATH_EXPRESSION =
        "//div[@id='userDropdown']//a[text() ='%s']";

    public DropdownLinkList(WebDriver driver, String id) {
        super(driver, id);
    }

    public void selectByVisibleText(String optionToSelect) {
        expandListOfOptions();
        selectItem(optionToSelect);
    }

    private void expandListOfOptions() {
       waitForElementToBeClickable(getElementLocator()).click();
    }

    private void selectItem(String optionToSelect) {
        final By itemLocator = By.xpath(
            String.format(DROPDOWN_ITEM_XPATH_EXPRESSION, optionToSelect));
        WebElement element = waitForElementToBeClickable(itemLocator);
        waitUtilBlockUINotDisplayed();
        element.click();
    }

}
