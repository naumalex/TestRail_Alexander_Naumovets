package elements;

import common.BaseElement;
import enums.common.BaseEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Radiobutton extends BaseElement {
    private static final String ELEMENT_XPATH_EXPRESSION =
        "//div[@class = 'column project-type']//label/strong[text()='%s']";

//    private final String label;

    public Radiobutton(WebDriver driver) {
        super(driver);
      //  this.label = label;
    }

    public void selectByEnumValue(BaseEnum enumValue) {
        if (enumValue == null) {
            return;
        }
        selectByVisibleText(enumValue.getName());
    }

    public void selectByVisibleText(String text) {
        By elementLocator = By.xpath(String.format(ELEMENT_XPATH_EXPRESSION, text));
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        driver.findElement(elementLocator).click();
    }
}
