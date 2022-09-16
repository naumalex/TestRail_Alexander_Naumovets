package data_providers;

import org.testng.annotations.DataProvider;
import utils.ProjectFactory;
import utils.TestCaseFactory;

public class ProjectDataProvider {
    @DataProvider(name = "addProjectDataProvider")
    public static Object[][] testData() {
        return new Object[][]{
            {ProjectFactory.getMandatoryInfoOnlyProject()},
            {ProjectFactory.getFullInfoProject()}
        };
    }

    @DataProvider(name = "editProjectDataProvider")
    public static Object[][] editProjectTestData() {
        return new Object[][]{
            {ProjectFactory.getFullInfoProject()}
        };
    }
}
