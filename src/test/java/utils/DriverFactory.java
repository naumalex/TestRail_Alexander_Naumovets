package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static WebDriver getDriver(String browserName) throws Exception {
        WebDriver driver;
        switch (browserName.toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-notifications");
                driver = new ChromeDriver(options);
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--ignore-certificate-errors");
                firefoxOptions.addArguments("--disable-popup-blocking");
                firefoxOptions.addArguments("--disable-notifications");
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability("headless", true);
                break;
            default:
                throw new Exception("Undefined Browser Type");
        }
        return driver;
    }
}
