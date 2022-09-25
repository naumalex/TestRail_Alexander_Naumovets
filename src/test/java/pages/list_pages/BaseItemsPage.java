package pages.list_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;

public abstract class BaseItemsPage extends HomePage {
    private static final String LIST_ITEM_XPATH_EXPRESSION =
        "//div[@id='content-inner']//td//*[text()='%s']";
    private static final String DELETE_LIST_ITEM_ICON_XPATH_EXPRESSION =
        LIST_ITEM_XPATH_EXPRESSION
            + "/ancestor::td/following-sibling::td//div[contains(@class, 'icon-small-delete')]/parent::a";
    private static final String EDIT_LIST_ITEM_ICON_XPATH_EXPRESSION =
        LIST_ITEM_XPATH_EXPRESSION
            + "/ancestor::td/following-sibling::td//div[contains(@class, 'icon-small-edit')]/parent::a";

    public BaseItemsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public abstract void waitForPageLoaded();

    public boolean isItemPresent(String itemText) {
        By locator =
            By.xpath(String.format(LIST_ITEM_XPATH_EXPRESSION, itemText));
        return isElementPresent(locator);
    }

    public boolean isItemDisappeared(String itemText) {
        By locator =
            By.xpath(String.format(LIST_ITEM_XPATH_EXPRESSION, itemText));
        if (!isItemPresent(itemText)) {
            return true;
        }
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }
        catch(TimeoutException exception) {
            return false;
        }
        return true;
    }

    public void clickDeleteItem(String itemText) {
        By deleteIconLocator = By.xpath(
            String.format(DELETE_LIST_ITEM_ICON_XPATH_EXPRESSION, itemText));
        WebElement element = driver.findElement(deleteIconLocator);
        new Actions(driver).moveToElement(element).build().perform();
        waitForElementToBeClickable(deleteIconLocator).click();
    }

    public void clickEditItem(String itemText) {
        By editIconLocator = By.xpath(
            String.format(EDIT_LIST_ITEM_ICON_XPATH_EXPRESSION , itemText));
        WebElement element = driver.findElement(editIconLocator);
        new Actions(driver).moveToElement(element).build().perform();
        waitForElementToBeClickable(editIconLocator)
        .click();
    }
}
