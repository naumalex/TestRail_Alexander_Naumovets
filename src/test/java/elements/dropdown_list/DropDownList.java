package elements.dropdown_list;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DropDownList extends BaseDropDownList {
    private static final String DROP_DOWN_XPATH_EXPRESSION =
        "//select[@id='%s']/following-sibling::div/a";
    private static final String DROP_DOWN_ITEM_XPATH_EXPRESSION =
        "//select[@id='%s']/following-sibling::div//li";
    private final String id;

    public DropDownList(WebDriver driver, String id) {
        super(driver);
        this.id = id;
    }

    public DropDownList(WebDriver driver, String id, boolean isWaitForDOMUpgrade) {
        super(driver, isWaitForDOMUpgrade);
        this.id = id;
    }

    @Override
    protected By getDropdownListLocator() {
        return By.xpath(String.format(DROP_DOWN_XPATH_EXPRESSION, id));
    }

    @Override
    protected By getDropdownListItemsLocator() {
        return By.xpath(String.format(DROP_DOWN_ITEM_XPATH_EXPRESSION, id));
    }
}
