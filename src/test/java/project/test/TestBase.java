package project.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import project.helpers.DriverFactory;

public class TestBase {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = DriverFactory.createDriver();
    }

    @AfterTest
    public void shotDown() {
        driver.close();
    }
}
