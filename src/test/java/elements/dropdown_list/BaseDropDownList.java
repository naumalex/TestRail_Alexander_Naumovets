package elements.dropdown_list;

import common.BaseElement;
import enums.common.BaseEnum;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

@Log4j2
public abstract class BaseDropDownList extends BaseElement {
    private boolean isWaitForDOMUpdate = false;

    public BaseDropDownList(WebDriver driver) {
        super(driver);
    }

    public BaseDropDownList(WebDriver driver,
                            boolean isWaitForDOMUpgrade) {
        this(driver);
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
        return waitForElementToBeClickable(getDropdownListLocator()).getText();
    }

    protected abstract By getDropdownListLocator();

    protected abstract By getDropdownListItemsLocator();

    private void expandListOfOptions() {
        WebElement element = waitForElementToBeClickable(getDropdownListLocator());
        waitUtilBlockUINotDisplayed();
        AllureUtils.attachScreenshot(driver);
        Actions actions = new Actions(driver);
        log.info(element.getText());
        actions.moveToElement(element).click().build().perform();
        AllureUtils.attachScreenshot(driver);
    }

    private void selectOption(String option) {
        driver.findElements(getDropdownListItemsLocator()).stream()
            .filter(p -> p.getAttribute("textContent").replace("\n", "").trim()
                .equals(option)).findFirst()
            .ifPresentOrElse(p -> {
                    if (!p.isDisplayed()) {
                        scrollIntoView(p);
                        AllureUtils.attachScreenshot(driver);
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
