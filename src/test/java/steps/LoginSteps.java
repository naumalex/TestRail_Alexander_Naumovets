package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

@Log4j2
public class LoginSteps {

    private final LoginPage loginPage;
    private final HomePage homepage;

    public LoginSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        homepage = new HomePage(driver);
    }

    @Step
    public void login(String email, String password) {
        log.info("Open Login window");
        loginPage.open();
        loginPage.waitForPageLoaded();
        log.info("Fill Email");
        loginPage.enterEmail(email);
        log.info("Fill Password");
        loginPage.enterPassword(password);
        log.info("Click Login button");
        loginPage.clickLogInButton();
        homepage.waitForPageLoaded();
    }

    @Step
    public void logout() {
        log.info("Logout");
        homepage.logout();
        loginPage.waitForPageLoaded();
    }
}
