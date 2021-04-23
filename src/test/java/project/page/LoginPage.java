package project.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    // определения локатора кнопки "войти"

    @FindBy(className = "desk-notif-card__login-new-item-title")
    public WebElement loginButton;

    // определения локатора поля ввода логина

    @FindBy(id = "passp-field-login")
    public WebElement yandexLogin;

    // определения локатора поля ввода пароля

    @FindBy(id = "passp-field-passwd")
    public WebElement yandexPassword;

    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage open() {
        driver.navigate().to("https://www.yandex.ru/");
        loginButton.click();
        return this;
    }

    public LoginPage sendLogin(String text) {
        yandexLogin.sendKeys(text);
        yandexLogin.sendKeys(Keys.RETURN);
        return this;
    }

    public LoginPage sendPassword(String text) {
        yandexPassword.sendKeys(text);
        yandexPassword.sendKeys(Keys.RETURN);
        return this;
    }

}