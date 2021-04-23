package project.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;


public class ProfilePage {

    @FindBy(className = "desk-notif-card__mail-title")
    public WebElement profileButton;

    @FindBy(className = "search-input__right-buttons")
    public WebElement searchButton;

    @FindBy(className = "mail-MessagesSearchInfo-Title_misc nb-with-xs-left-gap")
    public WebElement searchResult;

    public WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProfilePage openMail() {
        profileButton.click();
        return this;
    }

    public ProfilePage searchMail(String text) {
        ArrayList<String>tabs_windows = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(1));
        searchButton.click();
        searchButton.sendKeys(text);
        searchButton.sendKeys(Keys.RETURN);
        return this;
    }

    // тут будет метод получения количества писем



}