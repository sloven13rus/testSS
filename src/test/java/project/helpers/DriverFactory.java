package project.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


/**
 * Вспомогательный класс для создания экземляра драйвера
 */
public class DriverFactory {

    /**
     * Инстанс класса
     */
    private static DriverFactory instance;

    /**
     * Конструктор класса
     */
    private DriverFactory() {
    }

    /**
     * Геттер инстанса DriverFactory
     *
     * @return Инстанс DriverFactory
     */
    public static DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }
        return instance;
    }

    /**
     * Вспомогательный метод загрузки дравера
     *
     * @param browser браузер
     */
    private void setupDriver(DriversEnum browser) {
        switch (browser) {
            case CHROME -> WebDriverManager.chromedriver().setup();
            case FIREFOX -> WebDriverManager.firefoxdriver().setup();
            case EDGE -> WebDriverManager.edgedriver().setup();
            case OPERA -> WebDriverManager.operadriver().setup();
            case IE -> WebDriverManager.iedriver().setup();
        }
    }

    /**
     * Метод создания экземпляра драйвера
     *
     * @param browser         браузер
     * @param pageLoadTimeout явное ожидание загрузки страницы
     * @param implicitlyWait  явное ожидание появление элемента в DOM
     * @return WebDriver
     */
    private WebDriver createDriver(DriversEnum browser,
                                   Long pageLoadTimeout,
                                   Long implicitlyWait) {
        setupDriver(browser);
        WebDriver driver = switch (browser) {
            case CHROME -> new ChromeDriver();
            case FIREFOX -> new FirefoxDriver();
            case EDGE -> new EdgeDriver();
            case OPERA -> new OperaDriver();
            case IE -> new InternetExplorerDriver();
        };
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Метод создания экземпляра драйвера
     *
     * @return WebDriver
     * @throws IOException если файл конфигурации не найден
     */
    public static WebDriver createDriver() throws IOException {
        return getInstance().createDriver(
                DriversEnum.valueOf(
                        PropertiesReader.getProperty(
                                "browser")
                ),
                Long.parseLong(
                        PropertiesReader.getProperty(
                                "pageLoadTimeout"
                        )

                ),
                Long.parseLong(
                        PropertiesReader.getProperty(
                                "implicitlyWait"
                        )

                )
        );
    }
}