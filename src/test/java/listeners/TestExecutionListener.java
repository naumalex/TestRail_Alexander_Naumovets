package listeners;
import utils.AllureUtils;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestExecutionListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        IInvokedMethodListener.super.afterInvocation(method, testResult);
        if (testResult.getStatus() == ITestResult.FAILURE) {
            AllureUtils.attachScreenshot((WebDriver)testResult
                .getTestContext().getAttribute("driver"));
        }
    }
}