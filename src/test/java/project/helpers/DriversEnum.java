package project.helpers;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public enum DriversEnum {
    CHROME(),
    FIREFOX(),
    EDGE(),
    OPERA(),
    IE()
}
