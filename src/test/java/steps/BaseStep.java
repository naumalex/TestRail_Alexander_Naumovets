package steps;

import common.BaseElement;
import org.openqa.selenium.WebDriver;


public class BaseStep extends BaseElement {
    public BaseStep(WebDriver driver) {
        super(driver);
    }

    public void reloadPage() {
        driver.navigate().refresh();
    }
}
