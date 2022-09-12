package elements;

import common.BaseElement;
import models.TestStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StepInput extends BaseElement {
    private static final String STEP_DESCRIPTION_XPATH_EXPRESSION =
        "//table[@id='custom_steps_separated_table']//td[@class='step-no']/div[text()='%d']" +
            "/parent::td//following-sibling::td//div[@placeholder = 'Step Description']";

    private static final String STEP_EXPECTED_RESULT_XPATH_EXPRESSION =
        "//table[@id='custom_steps_separated_table']//td[@class='step-no']/div[text()='%d']" +
            "/parent::td//following-sibling::td//div[@placeholder = 'Expected Result']";

    private final int stepNumber;

    public StepInput(WebDriver driver, int stepNumber) {
        super(driver);
        this.stepNumber = stepNumber + 1;
    }

    public void fillStep(TestStep inputStep) {
        By StepDescriptionLocator = By.xpath(String.format(
            STEP_DESCRIPTION_XPATH_EXPRESSION, stepNumber));
        wait.until(ExpectedConditions.elementToBeClickable(StepDescriptionLocator));
        driver.findElement(StepDescriptionLocator)
            .sendKeys(inputStep.getDescription());
        driver.findElement(By.xpath(String.format(STEP_EXPECTED_RESULT_XPATH_EXPRESSION, stepNumber)))
            .sendKeys(inputStep.getExpectedResult());
    }
}
