package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Input extends BaseInputElement {

    public Input(WebDriver driver, String id) {
        super(driver, id);
    }

    public void setText(String text) {
        if (text == null)
        {
            return;
        }
        WebElement element = driver.findElement(By.cssSelector(String.format(ELEMENT_SELECTOR, id)));
        scrollIntoView(element);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
    }
}
