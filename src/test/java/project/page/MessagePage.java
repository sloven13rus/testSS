package project.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static org.awaitility.Awaitility.*;

import org.awaitility.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MessagePage extends BasePage {

    // строка поиска в сообщениях
    @FindBy(className = "textinput__control")
    private WebElement searchButton;

    // кнопка фильтра поиска в папках
    @FindBy(xpath = "//*[.='Папки']")
    private WebElement searchByFolders;

    // кнопка фильтра по входящим сообщениям
    @FindBy(xpath = "//span[contains(@class, 'menu__text') and text() = 'Входящие']")
    private WebElement searchByIncoming;

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
    @FindBy(className = "ComposeControlPanelButton-Button")
    private WebElement sendButton;

    public MessagePage(WebDriver driver) {
        super(driver);
    }

    public MessagePage refresh() {
        getDriver().navigate().refresh();
        getDriver().switchTo().alert().accept();
        return this;
    }

    // метод ввода поискового запроса
    private MessagePage sendSearchText(String text) {
        with().pollInterval(Duration.ONE_MILLISECOND)
                .await()
                .atMost(Duration.ONE_MINUTE)
                .until(searchButton::isDisplayed);
        searchButton.click();
        searchButton.sendKeys(text);
        searchButton.sendKeys(Keys.RETURN);
        searchByFolders.click();
        searchByIncoming.click();
        return this;
    }

    public int searchMailCount(String text) {
        return sendSearchText(text)
                .getNumbersOfEmail();
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
    private MessagePage sendEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    /**
     * Метод ввода темы письма
     *
     * @param theme тема письма
     * @return MessagePage
     */
    private MessagePage sendTheme(String theme) {
        themeInput.sendKeys(theme);
        return this;
    }

    /**
     * Метод ввода текста письма
     *
     * @return MessagePage
     */
    private MessagePage sendText(Integer countMails) {
        String emailText = String.format(
                "Найдено %d писем/ма", countMails
        );
        messageInput.sendKeys(emailText);
        return this;
    }

    /**
     * Метод нажатия кнопки отправки письма
     *
     */
    private void clickSendButton() {
        sendButton.click();
    }

    /**
     * Метод отправки письма
     *
     * @param email адрес
     * @param theme тема письма
     * @return MessagePage
     */
    public MessagePage sendMessage(String email,
                                   String theme,
                                   Integer count) {
        newEmailButton()
                .sendEmail(email)
                .sendTheme(theme)
                .sendText(count)
                .clickSendButton();
        return this;
    }

    // метод подсчета количества писем
    private int getNumbersOfEmail() {
        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className(
                "mail-MessagesSearchInfo_Summary"
        )));
        return Integer
                .parseInt(
                        getDriver()
                                .findElement(
                                        By.className(
                                                "mail-MessagesSearchInfo-Title_misc"
                                        )
                                )
                                .getText()
                                .replaceAll(
                                        "[^0-9]",
                                        "")
                );
    }
}