package elements;

import common.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseInputElement extends BaseElement {

    protected static final String ELEMENT_SELECTOR = "#%s";
    protected final String id;

    public BaseInputElement(WebDriver driver, String id) {
        super(driver);
        this.id = id;
    }

    public By getElementLocator() {
        return By.cssSelector(String.format(ELEMENT_SELECTOR, id));
    }

}
