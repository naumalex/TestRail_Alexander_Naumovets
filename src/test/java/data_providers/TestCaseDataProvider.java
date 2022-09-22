package data_providers;

import org.testng.annotations.DataProvider;
import utils.TestCaseFactory;

public class TestCaseDataProvider {
    @DataProvider(name = "textTemplateTestCaseDataProvider")
    public static Object[][] textTemplateData() {
        return new Object[][]{{TestCaseFactory.getMandatoryInfoOnlyTestCaseTextTemplate()},
            {TestCaseFactory.getTextTemplateFullInfoTestCase()}};
    }

    @DataProvider(name = "stepsTemplateTestCaseDataProvider")
    public static Object[][] stepsTemplateData() {
        return new Object[][]{{TestCaseFactory.getStepsTemplateFullInfoTestCase()}};
    }

    @DataProvider(name = "exploratorySessionTemplateTestCaseDataProvider")
    public static Object[][] exploratorySessionTemplateData() {
        return new Object[][]{{TestCaseFactory.getExploratorySessionFullInfoTestCase()}};
    }

    @DataProvider(name = "editTextTemplateTestCaseDataProvider")
    public static Object[][] editTextTemplateData() {
        return new Object[][]{
            {
                TestCaseFactory.getMandatoryInfoOnlyTestCaseTextTemplate(),
                TestCaseFactory.getTextTemplateFullInfoTestCase()
            }
        };
    }
}