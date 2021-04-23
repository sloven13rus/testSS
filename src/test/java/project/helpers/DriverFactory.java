package project.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver createDriver() {
        setDriverPath();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(95, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(95, TimeUnit.SECONDS);
        return driver;
    }

    private static void setDriverPath() {
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/Google/Chrome/Application/chromedriver.exe");
    }
}
