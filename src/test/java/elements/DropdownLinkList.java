package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
       WebElement element = driver.findElement(By.cssSelector(
           String.format(ELEMENT_SELECTOR, id)));
       scrollIntoView(element);
       wait.until(ExpectedConditions.visibilityOf(element));
       wait.until(ExpectedConditions.elementToBeClickable(element));
       element.click();
    }

    private void selectItem(String optionToSelect) {
        final By itemLocator = By.xpath(
            String.format(DROPDOWN_ITEM_XPATH_EXPRESSION, optionToSelect));
        wait.until(ExpectedConditions.elementToBeClickable(itemLocator));
        waitUtilBlockUINotDisplayed();
        driver.findElement(itemLocator).click();
    }

}
