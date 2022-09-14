package pages;

import elements.DropdownLinkList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private static final String USER_DROPDOWN_LINK_ID = "navigation-user";
    private static final By ADMINISTRATION_LINK_LOCATOR = By.cssSelector("#navigation-admin");
    private final static String PAGE_HEADER_XPATH_EXPRESSION =
        "//div[@id = 'content-header']//div[contains(text(), '%s')]";
    private final static By SAVE_RESULTS_MESSAGE = By.cssSelector(
        "#content-inner .message.message-success");
    private final static String SIDE_NAVIGATION_MENU_ITEM =
        "//div[@id = 'sidebar']//li/a[text()='%s']";
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("#" + USER_DROPDOWN_LINK_ID)));
    }

    public void logout() {
        new DropdownLinkList(driver, USER_DROPDOWN_LINK_ID)
            .selectByVisibleText("Logout");
    }

    public By getPageHeaderLocator(String pageTitle) {
        return By.xpath(String.format(PAGE_HEADER_XPATH_EXPRESSION, pageTitle));
    }

    public String getSaveResultsMessageText() {
        return driver.findElement(SAVE_RESULTS_MESSAGE).getText();
    }

    public void clickAdministrationLink() {
        WebElement element = driver.findElement(ADMINISTRATION_LINK_LOCATOR);
        scrollIntoView(element);
        element.click();
    }

    public void selectItemInSideNavigationMenu(String menuItem) {
        WebElement element = driver.findElement(
            By.xpath(String.format(SIDE_NAVIGATION_MENU_ITEM, menuItem)));
        scrollIntoView(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
