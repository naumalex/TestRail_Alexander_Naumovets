package elements;

import common.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListItemText extends BaseElement {
    private final String TEXT_LIST_ITEM_XPATH_EXPRESSION =
        "//div[@id = 'content-inner']//div[@class='field-title']/span[text()='%s']/parent::div/" +
            "following-sibling::div[@class='field-content'][1]";

    private final String label;

    public ListItemText(WebDriver driver, String label) {
        super(driver);
        this.label = label;
    }

    public String getText() {
        By textElementLocator = By.xpath(
            String.format(TEXT_LIST_ITEM_XPATH_EXPRESSION, label));
        if (!isElementPresent(textElementLocator)) {
            return null;
        }
        WebElement textElement = driver.findElement(textElementLocator);
        scrollIntoView(textElement);
        return textElement.getAttribute("innerText");
    }
}

