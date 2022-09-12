package elements;

import common.BaseElement;
import models.TestStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ListItemTable extends BaseElement {

    private final String TEXT_LIST_ITEM_DESCRIPTION_XPATH_EXPRESSION =
        "//div[@id = 'content-inner']//div[@class='field-title']/span[text()='%s']" +
        "/parent::div/following-sibling::div[@class='field-content']//td[@class='step-content']";
    private final String TEXT_LIST_ITEM_EXPECTED_RESULT_XPATH_EXPRESSION =
    "//div[@id = 'content-inner']//div[@class='field-title']/span[text()='%s']" +
        "/parent::div/following-sibling::div[@class='field-content']" +
        "//td[@class='step-content hidden-vertical']";


    private final String label;

    public ListItemTable(WebDriver driver, String label) {
        super(driver);
        this.label = label;
    }

    public List<TestStep> getStructuredValue() {
        List<WebElement> descriptionElements = driver.findElements(By.xpath(
            String.format(TEXT_LIST_ITEM_DESCRIPTION_XPATH_EXPRESSION, label)));
        List<WebElement> expectedResultsElements = driver.findElements(By.xpath(
            String.format(TEXT_LIST_ITEM_EXPECTED_RESULT_XPATH_EXPRESSION, label)));
        if (descriptionElements.isEmpty()) {
            return null;
        }
        List<TestStep> structuredSteps = new ArrayList<>();
        for (int i = 0; i < descriptionElements.size(); i++) {
            structuredSteps.add(TestStep.builder()
                .description(descriptionElements.get(i).getText())
                .expectedResult(expectedResultsElements.get(i).getText())
                .build());
        }
        return structuredSteps;
    }
}

