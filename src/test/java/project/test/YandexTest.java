package project.test;

import org.testng.annotations.Test;
import project.page.LoginPage;


public class YandexTest extends TestBase {

    @Test
    public void yandexTest() {
        new LoginPage(driver)
                .open()
                .sendLogin("sloventest@yandex.ru")
                .sendPassword("a35bc6Ametist");
    }
}
