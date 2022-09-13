package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class Checkbox extends BaseInputElement {
    public Checkbox(WebDriver driver, String id) {
        super(driver, id);
    }
    public void setValue(boolean value) {
        WebElement element = driver.findElement(getElementLocator());
        if (!(element.isSelected() == value)) {
            log.debug(String.format("Scroll to checkbox with id = %s", id));
            scrollIntoView(element);
            log.debug(String.format("Click checkbox with id = %s", id));
            element.click();
        }
    }
}
