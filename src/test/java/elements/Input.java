package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Input extends BaseInputElement {

    private final By inputLocator;
    public Input(WebDriver driver, String id) {
        super(driver, id);
        inputLocator = getElementLocator();
    }

    public void setText(String text) {
        if (text == null)
        {
            return;
        }
        WebElement element = waitForElementToBeClickable(inputLocator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText() {
        WebElement element = driver.findElement(inputLocator);
        scrollIntoView(element);
        return element.getAttribute("value");
    }
}
