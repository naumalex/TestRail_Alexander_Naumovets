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

    public void setValue(boolean value) {
        WebElement element = driver.findElement(getElementLocator());
        if (!(element.isSelected() == value)) {
            log.debug(String.format("Scroll to checkbox with locator = %s",
                getElementLocator().toString()));
            waitForElementToBeClickable(element);
            log.debug(String.format("Click checkbox with locator = %s",
                getElementLocator().toString()));
            element.click();
        }
    }
}
