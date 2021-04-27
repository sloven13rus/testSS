package project.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MessagePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[8]/div/div[3]/div[2]/div[2]/div/div/a")
    public WebElement writeButton;

    @FindBy(className = "composeYabbles")
    public WebElement emailInput;

    @FindBy(className = "composeTextField")
    public WebElement themeInput;

    @FindBy(css = "div[placeholder='Напишите что-нибудь']")
    public WebElement messageInput;

    @FindBy(className = "button2__text")
    public WebElement sendButton;

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[8]/div/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/form/div/span/input")
    public WebElement secondSearchButton;

    public MessagePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод нажатия кнопки нового письма
     *
     * @return MessagePage
     */
    private MessagePage newEmailButton() {
        writeButton.click();
        return this;
    }

    /**
     * Метод ввода адреса письма
     *
     * @param email адрес получателя
     * @return MessagePage
     */
    public MessagePage sendEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    /**
     * Метод ввода темы письма
     *
     * @param theme тема письма
     * @return MessagePage
     */
    public MessagePage sendTheme(String theme) {
        themeInput.sendKeys(theme);
        return this;
    }

    /**
     * Метод ввода текста письма
     *
     * @return MessagePage
     */
    private MessagePage sendText() {
        messageInput.sendKeys("Найдено");
        return this;
    }

    /**
     * Метод нажатия кнопки отправки письма
     *
     * @return MessagePage
     */
    private MessagePage clickSendButton() {
        sendButton.click();
        return this;
    }

    /**
     * Метод отправки письма
     *
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
     *
     * @param text поисковый запрос
     * @return MessagePage
     */
    private MessagePage sendSecondSearchText(String text) {
        secondSearchButton.click();
        secondSearchButton.sendKeys(text);
        secondSearchButton.sendKeys(Keys.RETURN);
        return this;
    }

    /**
     * Метод возвращает количество писем из результатов поиска
     *
     * @return количество писем
     */
    private int getSecondNumbersOfEmail() {
        Pattern pattern = Pattern.compile("\\d*");
        Matcher matcher = pattern.matcher(secondSearchButton.getText());
        return Integer.parseInt(matcher.group());
    }

    /**
     * Метод возвращает количество писем из результатов поиска
     *
     * @param text поисковый запрос
     * @return количество писем из результатов поиска
     */
    public int searchSecondMailCount(String text) {
        return sendSecondSearchText(text).getSecondNumbersOfEmail();
    }
}
