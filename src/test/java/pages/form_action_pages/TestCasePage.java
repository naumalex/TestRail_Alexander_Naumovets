package pages.form_action_pages;

import elements.DropDownList;
import elements.Input;
import elements.StepInput;
import enums.test_case.Template;
import models.TestCase;
import models.TestStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;

import java.util.List;

public class TestCasePage extends BaseFormActionPage {

    private static final By ADD_THE_FIRST_STEP_LINK_LOCATOR = By.xpath(
        "//div[@id = 'custom_steps_separated_container']" +
            "//a[text()='Add the first step']");
    private static final By ADD_STEP_LINK_LOCATOR = By.xpath(
        "//a[@class='addStep' and text() = 'Add Step']");

    public TestCasePage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(TestCase inputTestCase) {
        new Input(driver, "title").setText(inputTestCase.getTitle());
        new DropDownList(driver, "section_id").selectByVisibleText(
            inputTestCase.getSection());

        if (inputTestCase.getTemplate() == Template.TEST_CASE_TEXT) {
            new DropDownList(driver, "template_id").selectByEnumValue(
                inputTestCase.getTemplate());
        }
        else {
            new DropDownList(driver, "template_id", true).selectByEnumValue(
                inputTestCase.getTemplate());
        }
        new DropDownList(driver, "type_id").selectByEnumValue(
            inputTestCase.getType());
        new DropDownList(driver, "priority_id").selectByEnumValue(
            inputTestCase.getPriority());
        new Input(driver, "estimate").setText(inputTestCase.getEstimate());
        new Input(driver, "refs").setText(inputTestCase.getReference());
        new DropDownList(driver, "custom_automation_type").selectByEnumValue(
            inputTestCase.getAutomationType());
        new Input(driver, "custom_mission_display").setText(inputTestCase.getMission());
        new Input(driver, "custom_goals_display").setText(inputTestCase.getGoals());
        new Input(driver, "custom_preconds_display").setText(inputTestCase.getPreconditions());
        new Input(driver, "custom_steps_display").setText(inputTestCase.getSteps());
        new Input(driver, "custom_expected_display").setText(inputTestCase.getExpectedResult());
        fillSteps(inputTestCase.getStepsStructured());
    }

    private void clickAddTheFirstStepLink() {
        driver.findElement(ADD_THE_FIRST_STEP_LINK_LOCATOR).click();
    }

    private void clickAddStepLink() {
        driver.findElement(ADD_STEP_LINK_LOCATOR).click();
    }

    private void fillSteps(List<TestStep> steps) {
        if ((steps == null) || (steps.isEmpty())) {
            return;
        }
        clickAddTheFirstStepLink();
        new StepInput(driver, 0).fillStep(steps.get(0));
        for (int i = 1; i < steps.size(); i++) {
            clickAddStepLink();
            new StepInput(driver, i).fillStep(steps.get(i));
        }
    }
}
