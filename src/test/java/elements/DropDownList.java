package elements;

import common.BaseElement;
import enums.common.BaseEnum;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class DropDownList extends BaseElement {
    private static final String DROP_DOWN_XPATH_EXPRESSION =
        "//select[@id='%s']/following-sibling::div";
    private static final String DROP_DOWN_ITEM_XPATH_EXPRESSION =
        "//select[@id='%s']/following-sibling::div//li";
    private final By dropdownLocator;
    private final By dropdownItemLocator;
    private boolean isWaitForDOMUpdate = false;
    private final String id;

    public DropDownList(WebDriver driver, String id) {
        super(driver);
        this.id = id;
        dropdownLocator = By.xpath(String.format(DROP_DOWN_XPATH_EXPRESSION, id));
        dropdownItemLocator = By.xpath(String.format(DROP_DOWN_ITEM_XPATH_EXPRESSION, id));
    }

    public DropDownList(WebDriver driver, String id, boolean isWaitForDOMUpgrade) {
        this(driver, id);
        this.isWaitForDOMUpdate = isWaitForDOMUpgrade;
    }

    public void selectByVisibleText(String visibleText) {
        if (visibleText == null)
            return;
        expandListOfOptions();
        selectOption(visibleText);
    }

    public void selectByEnumValue(BaseEnum enumValue) {
        if (enumValue == null)
            return;
        selectByVisibleText(enumValue.getName());
    }

    public String getValue() {
        WebElement element = driver.findElement(dropdownLocator);
        scrollIntoView(element);
        return element.getText();
    }

    private void expandListOfOptions() {
        WebElement element = waitForElementToBeClickable(dropdownLocator);
        waitUtilBlockUINotDisplayed();
        element.click();
    }

    private void selectOption(String option) {
        driver.findElements(dropdownItemLocator).stream()
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
