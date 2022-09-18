package elements;

import common.BaseElement;
import enums.common.BaseEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Radiobutton extends BaseElement {
    private static final String ELEMENT_XPATH_EXPRESSION =
        "//div[@class = 'radio']/label/strong[text()='%s']";
    private final static By SELECTED_VALUE_LOCATOR = By.xpath(
        "//div[@class='radio']//input[@checked = 'checked']" +
            "/preceding-sibling::strong");
    public Radiobutton(WebDriver driver) {
        super(driver);
    }

    public void selectByEnumValue(BaseEnum enumValue) {
        if (enumValue == null) {
            return;
        }
        selectByVisibleText(enumValue.getName());
    }

    public void selectByVisibleText(String text) {
        By elementLocator = By.xpath(String.format(ELEMENT_XPATH_EXPRESSION, text));
        waitForElementToBeClickable(elementLocator).click();
    }

    public String getSelectedValue() {
        WebElement radiobutton = driver.findElement(SELECTED_VALUE_LOCATOR);
        scrollIntoView(radiobutton);
        wait.until(ExpectedConditions.elementToBeClickable(radiobutton));
        return radiobutton.getText();
    }
}
