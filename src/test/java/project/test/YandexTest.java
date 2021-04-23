package project.test;

import org.testng.annotations.Test;
import project.page.LoginPage;
import project.page.MessagePage;
import project.page.ProfilePage;


public class YandexTest extends TestBase {

    @Test
    public void yandexTest() {
        new LoginPage(driver)
                .open()
                .sendLogin("sloventest@yandex.ru")
                .sendPassword("a35bc6Ametist");
        new ProfilePage(driver)
                .openMail()
                .searchMail("Simbirsoft theme");
        new MessagePage(driver)
                .writeMessage("sloventest@yandex.ru")
                .sendTheme("Simbirsoft theme")
                .sendMessage("Найдено n писем/ьма");

    }
}
