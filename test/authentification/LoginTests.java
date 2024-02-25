package authentification;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.PagesDefinition;
import singleton.WebDriverSingleton;

public class LoginTests {
    @Test
    public void testLogin() {
        WebDriver driver = WebDriverSingleton.getInstance();

        driver.get(PagesDefinition.AUTHENTIFICATION_PAGE);
    }
}
