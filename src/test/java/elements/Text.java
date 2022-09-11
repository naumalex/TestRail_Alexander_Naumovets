package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Text extends BaseInputElement {

    public Text(WebDriver driver, String id) {
        super(driver, id);
    }

    public String getText() {
        WebElement textElement = driver.findElement(
            By.cssSelector(String.format(ELEMENT_SELECTOR, id)));
        scrollIntoView(textElement);
        String innerText = textElement.getAttribute("innerText");
        String text = innerText.substring(innerText.lastIndexOf("\n") + 1);
        return !text.equals("None") && !text.equals("") ? text : null;
    }
}
