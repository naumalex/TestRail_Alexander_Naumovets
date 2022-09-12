package pages;

import common.BaseElement;
import org.openqa.selenium.WebDriver;

public abstract class BasePage extends BaseElement {

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public abstract void waitForPageLoaded();
}
