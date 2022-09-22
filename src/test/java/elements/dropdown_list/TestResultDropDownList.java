package elements.dropdown_list;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class TestResultDropDownList extends BaseDropDownList {
    private static final String DROP_DOWN_XPATH_EXPRESSION =
        "//tr[starts-with(@id, 'row-')]//td/a[contains(text(), '%s')]" +
            "/parent::td/following-sibling::td[@class='js-status']";
    private static final String DROP_DOWN_ITEMS_XPATH_EXPRESSION =
        "//div[@id='statusDropdown']//a";
    private final String testCaseTitle;

    public TestResultDropDownList(WebDriver driver, String testCaseTitle) {
        super(driver);
        this.testCaseTitle = testCaseTitle;
    }

    @Override
    protected By getDropdownListLocator() {
        return By.xpath(String.format(DROP_DOWN_XPATH_EXPRESSION, testCaseTitle));
    }

    @Override
    protected By getDropdownListItemsLocator() {
        return By.xpath(DROP_DOWN_ITEMS_XPATH_EXPRESSION);
    }
}
