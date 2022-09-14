package elements;

import common.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseInputElement extends BaseElement {

    protected static final String ELEMENT_SELECTOR = "#%s";
    protected final By locator;

    public BaseInputElement(WebDriver driver, String id) {
        super(driver);
        this.locator = By.cssSelector(String.format(ELEMENT_SELECTOR, id));
    }

    public BaseInputElement(WebDriver driver, By locator) {
        super(driver);
        this.locator = locator;
    }

    public By getElementLocator() {
        return locator;
    }

}
