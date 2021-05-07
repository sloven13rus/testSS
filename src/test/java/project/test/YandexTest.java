package project.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import project.helpers.PropertiesReader;
import project.page.LoginPage;
import project.page.MessagePage;

import java.io.IOException;


public class YandexTest extends TestBase {

    @Test
    public void yandexTest() throws IOException {
        int emailCount = new LoginPage(driver)
                .open()
                .login(
                        PropertiesReader.getProperty("login"),
                        PropertiesReader.getProperty("password"))
                .searchMailCount(PropertiesReader.getProperty("theme")
                );
        int secondEmailCount = new MessagePage(driver)
                .sendMessage(PropertiesReader.getProperty("email"),
                        PropertiesReader.getProperty("theme"),
                        emailCount)
                .refresh()
                .searchMailCount(PropertiesReader.getProperty("theme"));
        Assert.assertEquals(secondEmailCount, emailCount + 1, "Число писем не увеличилось");
    }
}
