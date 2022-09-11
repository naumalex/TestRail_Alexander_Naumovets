package tests;

import utils.DriverFactory;
import utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import steps.LoginSteps;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected LoginSteps loginSteps;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserNameInSuitXML,
                      ITestContext testContext) throws Exception {

        String browserNameInCommandLine =
            System.getProperty("browser");
        if (!(browserNameInCommandLine == null)
            && !browserNameInCommandLine.isEmpty()) {
            driver = DriverFactory.getDriver(browserNameInCommandLine);
        }
        else {
            driver = DriverFactory.getDriver(browserNameInSuitXML);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        testContext.setAttribute("driver", driver);
        loginSteps = new LoginSteps(driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginSteps.login(PropertyReader.getProperty("test_rail.all.email"),
            PropertyReader.getProperty("test_rail.all.password"));
    }

    @AfterMethod
    public void tearDown() {
       loginSteps.logout();
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}
