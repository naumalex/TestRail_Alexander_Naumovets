package pages.test_case;

import elements.ListItemTable;
import elements.ListItemText;
import elements.Text;
import enums.test_case.AutomationType;
import enums.test_case.Priority;
import enums.test_case.Template;
import enums.test_case.Type;
import models.TestCase;
import models.TestStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;

import java.util.List;

public class TestCaseDetailsPage extends HomePage {

    private final static By TYPE_CELL_LOCATOR = By.cssSelector("#cell_type_id");
    private final static By PAGE_HEADER_LOCATOR = By.cssSelector("#content-header .page_title");
    private final static By SECTION_LOCATOR = By.cssSelector(".content-breadcrumb");

    public TestCaseDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TYPE_CELL_LOCATOR));
    }

     public TestCase getTestCaseInfo(Template template) {
        String title = driver.findElement(PAGE_HEADER_LOCATOR).getAttribute("innerText");
        String section = getSection();
        String type = new Text(driver, "cell_type_id").getText();
        String priority = new Text(driver, "cell_priority_id").getText();
        String estimate = new Text(driver, "cell_estimate").getText();
        String reference = new Text(driver, "cell_refs").getText();
        String automationType = new Text(driver, "cell_custom_automation_type").getText();
        String preconditions = new ListItemText(driver, "Preconditions").getText();
        String mission = new ListItemText(driver, "Mission").getText();
        String goals = new ListItemText(driver, "Goals").getText();
        String steps = null;
        String expectedResult = null;
        List<TestStep> stepsStructured = null;
        if (template == Template.TEST_CASE_STEPS)
        {
            stepsStructured =
                new ListItemTable(driver, "Steps").getStructuredValue();
        }
        else {
            steps = new ListItemText(driver, "Steps").getText();
            expectedResult = new ListItemText(driver, "Expected Result").getText();
        }

        return TestCase.builder()
            .title(title)
            .section(section)
            .template(template)
            .type(Type.fromString(type))
            .priority(Priority.fromString(priority))
            .estimate(estimate)
            .reference(reference)
            .automationType(AutomationType.fromString(automationType))
            .preconditions(preconditions)
            .steps(steps)
            .expectedResult(expectedResult)
            .stepsStructured(stepsStructured)
            .mission(mission)
            .goals(goals)
            .build();
     }

     private String getSection() {
         String fullSectionText = driver.findElement(SECTION_LOCATOR).getAttribute("innerText");
         return fullSectionText.substring(fullSectionText.indexOf("›") + 1);
     }
}
