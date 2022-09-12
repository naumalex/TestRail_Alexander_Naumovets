package elements;

import enums.common.BaseEnum;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

@Log4j2
public class DropDownList extends BaseInputElement {
    private boolean isWaitForDOMUpdate = false;

    private static final String DROP_DOWN_XPATH_EXPRESSION =
        "//select[@id='%s']/following-sibling::div";
    private static final String DROP_DOWN_LIST_ITEM_XPATH_EXPRESSION =
        "//select[@id='%s']/following-sibling::div//li";

    public DropDownList(WebDriver driver, String id) {
        super(driver, id);
    }

    public DropDownList(WebDriver driver, String id, boolean isWaitForDOMUpgrade) {
        super(driver, id);
        this.isWaitForDOMUpdate = isWaitForDOMUpgrade;
    }

    public void selectByVisibleText(String visibleText) {
        if (visibleText == null)
            return;
        expandListOfOptions();
        selectOption(visibleText);
    }

    public  void selectByEnumValue(BaseEnum enumValue) {
        if (enumValue == null)
            return;
        selectByVisibleText(enumValue.getName());
    }

    private void expandListOfOptions() {
        By elementLocator = By.xpath(String.format(DROP_DOWN_XPATH_EXPRESSION, id));
        WebElement element = driver.findElement(elementLocator);
        scrollIntoView(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        waitUtilBlockUINotDisplayed();
        element.click();
    }

    private void selectOption(String option) {
        driver.findElements(By.xpath(String.format(
            DROP_DOWN_LIST_ITEM_XPATH_EXPRESSION, id))).stream()
            .filter(p -> p.getAttribute("textContent").replace("\n", "").trim()
                .equals(option)).findFirst()
            .ifPresentOrElse(p -> {
                if (!p.isDisplayed()) {
                    scrollIntoView(p);
                }
                wait.until(ExpectedConditions.visibilityOf(p));
                wait.until(ExpectedConditions.elementToBeClickable(p));
                waitUtilBlockUINotDisplayed();
                p.click();
                if (isWaitForDOMUpdate) {
                    wait.until(ExpectedConditions.stalenessOf(p));
                }
                },
                () -> log.error(String.format("Could not select option %s", option))
            );
    }
}
