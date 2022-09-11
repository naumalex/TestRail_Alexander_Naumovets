package pages;

import elements.DropdownLinkList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private static final String USER_DROPDOWN_LINK_ID ="navigation-user";
    private final static String PAGE_HEADER_XPATH_EXPRESSION =
        "//div[@id = 'content-header']//div[contains(text(), '%s')]";

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
}
