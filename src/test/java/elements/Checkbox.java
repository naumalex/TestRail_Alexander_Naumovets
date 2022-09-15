package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class Checkbox extends BaseInputElement {
    public Checkbox(WebDriver driver, String id) {
        super(driver, id);
    }

    public Checkbox(WebDriver driver, By locator) {
        super(driver, locator);
    }

    public boolean getValue() {
        WebElement element = driver.findElement(getElementLocator());
        scrollIntoView(element);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isSelected();
    }

    public void setValue(Boolean value) {
        if (value == null) {
            return;
        }
        WebElement element = waitForElementToBeClickable(getElementLocator());
        if (!(element.isSelected() == value)) {
            element.click();
        }
    }
}
