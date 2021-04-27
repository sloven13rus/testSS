package project.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.helpers.PropertiesReader;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginPage extends BasePage {

    // кнопка входа в почту

    @FindBy(xpath = "//*[@id=\"index-page-container\"]/div/div[2]/div/div/div[4]/a[2]")
    public WebElement loginButton;

    // поле ввода логина

    @FindBy(id = "passp-field-login")
    public WebElement yandexLogin;

    // поле ввода пароля

    @FindBy(id = "passp-field-passwd")
    public WebElement yandexPassword;

    // кнопка поиска в сообщениях
    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[8]/div/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/form/div/span/input")
    public WebElement searchButton;

    // строка результатов поиска
    @FindBy(className = "mail-MessagesSearchInfo-Title_misc nb-with-xs-left-gap")
    public WebElement searchResult;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод открытия страницы
     *
     * @return LoginPage
     * @throws IOException если файл конфигурации не найден
     */
    public LoginPage open() throws IOException {
        getDriver().navigate().to(
                PropertiesReader.getProperty("baseURL")
        );
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage(getDriver());
    }

    private LoginPage sendLogin(String text) {
        yandexLogin.sendKeys(text);
        yandexLogin.sendKeys(Keys.RETURN);
        return this;
    }

    private LoginPage sendPassword(String text) {
        yandexPassword.sendKeys(text);
        yandexPassword.sendKeys(Keys.RETURN);
        return this;
    }

    public LoginPage login(String login, String password) {
        sendLogin(login)
                .sendPassword(password);
        return new LoginPage(getDriver());
    }

    /**
     * Метод ввода поискового запроса
     *
     * @param text поисковый запрос
     * @return LoginPage
     */
    private LoginPage sendSearchText(String text) {
        searchButton.click();
        searchButton.sendKeys(text);
        searchButton.sendKeys(Keys.RETURN);
        return this;
    }

    /**
     * Метод возвращает количество писем из результатов поиска
     *
     * @return количество писем
     */
    private int getNumbersOfEmail() {
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(searchResult.getText());
        return Integer.parseInt(matcher.group());
    }

    /**
     * Метод возвращает количество писем из результатов поиска
     *
     * @param text поисковый запрос
     * @return количество писем из результатов поиска
     */
    public int searchMailCount(String text) {
        return sendSearchText(text).getNumbersOfEmail();
    }

}