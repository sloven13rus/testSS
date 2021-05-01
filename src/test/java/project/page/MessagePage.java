package project.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;


public class MessagePage extends BasePage {

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

    // кнопка "написать"
    @FindBy(className = "js-main-action-compose")
    private WebElement writeButton;

    // строка ввода получателя письма
    @FindBy(className = "composeYabbles")
    private WebElement emailInput;

    // строка ввода темы письма
    @FindBy(className = "composeTextField")
    private WebElement themeInput;

    // строка ввода текста ссобщения
    @FindBy(css = "div[placeholder='Напишите что-нибудь']")
    private WebElement messageInput;

    // кнопка "отправить"
    @FindBy(className = "button2__text")
    private WebElement sendButton;


    public MessagePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод нажатия кнопки нового письма
     * @return MessagePage
     */
    private MessagePage newEmailButton() {
        writeButton.click();
        return this;
    }

    /**
     * Метод ввода адреса письма
     * @param email адрес получателя
     * @return MessagePage
     */
    private MessagePage sendEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    /**
     * Метод ввода темы письма
     * @param theme тема письма
     * @return MessagePage
     */
    private MessagePage sendTheme(String theme) {
        themeInput.sendKeys(theme);
        return this;
    }

    /**
     * Метод ввода текста письма
     * @return MessagePage
     */
    private MessagePage sendText() {
        messageInput.sendKeys( "Найдено" + getNumbersOfEmail() + "писем/ма");
        return this;
    }

    /**
     * Метод нажатия кнопки отправки письма
     * @return MessagePage
     */
    private MessagePage clickSendButton() {
        sendButton.click();
        return this;
    }

    /**
     * Метод отправки письма
     * @param email адрес
     * @param theme тема письма
     * @return MessagePage
     */
    public MessagePage sendMessage(String email,
                                   String theme) {
        newEmailButton()
                .sendEmail(email)
                .sendTheme(theme)
                .sendText()
                .clickSendButton();
        return this;
    }

    /**
     * Метод ввода поискового запроса
     * @param text поисковый запрос
     * @return MessagePage
     */
    private MessagePage sendSearchText(String text) {
        searchButton.click();
        searchButton.sendKeys(text);
        searchButton.sendKeys(Keys.RETURN);
        searchByFolders.click();
        searchByIncoming.click();
        return this;
    }

    // метод подсчета количества писем
    private int getNumbersOfEmail() {
        ArrayList<WebElement> mailList = new ArrayList<>();
        mailList.add(foundMessage);
        return mailList.size();
    }

    public int searchSecondMailCount(String text) {
        return sendSearchText(text).getNumbersOfEmail();
    }
}