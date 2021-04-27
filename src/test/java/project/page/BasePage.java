package project.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Базовый класс для описания PageObject
 */
public class BasePage {

    /**
     * Экземпляр вебдрайвера
     */
    private final WebDriver driver;

    protected WebDriver getDriver() {
        return driver;
    }

    /**
     * Конструктор класса
     * @param driver вебдрайвер
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
