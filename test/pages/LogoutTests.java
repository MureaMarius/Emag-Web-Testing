package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.specificPages.AuthentificationPage;
import singleton.WebDriverSingleton;
import utilities.SpecialData;

public class LogoutTests {
    WebDriver driver;
    AuthentificationPage loginPage;

    @BeforeTest
    public void setUp(){
        driver = WebDriverSingleton.getInstance();

        loginPage = new AuthentificationPage(driver);
    }

    @BeforeMethod
    public void openLoginPage(){
        driver.get(PagesDefinition.AUTHENTIFICATION_PAGE);
    }

    @Test
    public void logOutTest() throws InterruptedException {
        loginPage.logInLogOutProcess(SpecialData.EMAIL, SpecialData.PASSWORD, 10000);

        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(PagesDefinition.AUTHENTIFICATION_PAGE));
    }

    @AfterTest
    public void quit(){
        driver.quit();
    }
}
