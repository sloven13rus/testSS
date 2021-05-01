package project.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.helpers.PropertiesReader;

import java.io.IOException;
import java.util.ArrayList;


public class LoginPage extends BasePage {


    // поле ввода логина
    @FindBy(id = "passp-field-login")
    private WebElement yandexLogin;

    // поле ввода пароля
    @FindBy(id = "passp-field-passwd")
    private WebElement yandexPassword;

    // строка поиска в сообщениях
    @FindBy(className = "textinput__control")
    private WebElement searchButton;

    // кнопка фильтра поиска в папках
    @FindBy(className = "svgicon-mail--AdvancedSearch_folder")
    private WebElement searchByFolders;

    // кнопка фильтра по входящим сообщениям
    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[9]/div/div/div[1]/span")
    private WebElement searchByIncoming;

    // строка с найденным сообщением
    @FindBy(className = "ns-viev-messages-item-wrap")
    private WebElement foundMessage;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод открытия страницы
     * @return LoginPage
     * @throws IOException если файл конфигурации не найден
     */
    public LoginPage open() throws IOException {
        getDriver().navigate().to(
                PropertiesReader.getProperty("baseURL")
        );
        return this;
    }

    // метод ввода логина
    private LoginPage sendLogin(String text) {
        yandexLogin.sendKeys(text);
        yandexLogin.sendKeys(Keys.RETURN);
        return this;
    }

    // метод ввода пароля
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

    private LoginPage sendSearchText(String text) {
        searchButton.click();
        searchButton.sendKeys(text);
        searchButton.sendKeys(Keys.RETURN);
        searchByFolders.click();
        searchByIncoming.click();
        return this;
    }

    private int getNumbersOfEmail() {
        ArrayList<WebElement> mailList = new ArrayList<>();
        mailList.add(foundMessage);
        return mailList.size();
    }

    public int searchMailCount(String text) {
        return sendSearchText(text).getNumbersOfEmail();
    }

}