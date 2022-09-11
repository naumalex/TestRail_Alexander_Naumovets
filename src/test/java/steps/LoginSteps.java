package steps;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps {

    private final LoginPage loginPage;
    private final HomePage homepage;

    public LoginSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        homepage = new HomePage(driver);
    }

    public void login(String email, String password) {
        loginPage.open();
        loginPage.waitForPageLoaded();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogInButton();
        homepage.waitForPageLoaded();
    }

    public void logout() {
        homepage.logout();
        loginPage.waitForPageLoaded();
    }

}
