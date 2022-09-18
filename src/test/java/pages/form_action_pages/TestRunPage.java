package pages.form_action_pages;

import elements.dropdown_list.DropDownList;
import elements.Input;
import elements.Radiobutton;
import models.TestRun;
import org.openqa.selenium.WebDriver;

public class TestRunPage extends BaseFormActionPage {
    public TestRunPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(TestRun inputTestRun) {
        new Input(driver, "name").setText(inputTestRun.getName());
        new Input(driver, "refs").setText(inputTestRun.getReferences());
        new DropDownList(driver, "milestone_id").selectByVisibleText(inputTestRun.getMilestone());
        new DropDownList(driver, "assignedto_id").selectByVisibleText(inputTestRun.getAssignTo());
        new Input(driver, "description_display").setText(inputTestRun.getDescription());
        new Radiobutton(driver).selectByEnumValue(inputTestRun.getIncludeTestCasesMode());
    }
}
