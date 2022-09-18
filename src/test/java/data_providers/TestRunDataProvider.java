package data_providers;

import org.testng.annotations.DataProvider;
import utils.ProjectFactory;
import utils.TestCaseFactory;
import utils.TestRunFactory;

import java.lang.reflect.Array;
import java.util.List;

public class TestRunDataProvider {
    @DataProvider(name = "testRunDataProvider")
    public static Object[][] testData() {
        return new Object[][]{
            {TestCaseFactory.getTestCasesList(), TestRunFactory.getMandatoryInfoOnlyTestRun()}
        };
    }
}
