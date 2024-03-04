package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.definitions.AuthElementsDefinition;
import pages.definitions.PagesDefinition;
import pages.specificPages.AuthentificationPage;
import singleton.WebDriverSingleton;
import utilities.SpecialData;

import java.util.ArrayList;
import java.util.List;

public class LoginPageTests {
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
    public void testLoginWithoutCredentials() throws InterruptedException {
        loginPage.login("", "", 0);

        Assert.assertTrue(driver.findElement(By.xpath(AuthElementsDefinition.MISSING_FIELD_PATH)).getText().
                equalsIgnoreCase(AuthElementsDefinition.MISSING_FIELD_MESSAGE));
    }

    @Test
    public void testLoginWithCredentials() throws InterruptedException {
        SpecialData specialData = new SpecialData();
        loginPage.login(specialData.getEmail(), specialData.getPassword(), 10000);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(PagesDefinition.MY_ACCOUNT_PAGE));

        loginPage.logout();
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(PagesDefinition.AUTHENTIFICATION_PAGE));
    }

    @Test
    public void testDifferentAccountTypeLogin() {
        loginPage.clickOnSpecificLoginButton("facebook account");

        Assert.assertTrue(driver.getCurrentUrl().contains(PagesDefinition.FACEBOOK_ACCOUNT_REDIRECT_PAGE));

        driver.navigate().back();
        loginPage.clickOnSpecificLoginButton("google account");

        Assert.assertTrue(driver.getCurrentUrl().contains(PagesDefinition.GOOGLE_ACCOUNT_REDIRECT_PAGE));

        driver.navigate().back();
        loginPage.clickOnSpecificLoginButton("apple account");

        Assert.assertTrue(driver.getCurrentUrl().contains(PagesDefinition.APPLE_ACCOUNT_REDIRECT_PAGE));
    }

    @Test
    public void testHelpRedirectPage() throws InterruptedException {
        loginPage.clickOnSpecificLoginButton("help button");

        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));

        Thread.sleep(2000);
        Assert.assertTrue(driver.switchTo().window(browserTabs.get(1)).getCurrentUrl().equalsIgnoreCase(PagesDefinition.HELP_REDIRECT_PAGE));
    }

    @Test
    public void testHomeRedirectPage() {
        loginPage.clickOnSpecificLoginButton("home button");

        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(PagesDefinition.HOME_PAGE));
    }

    @AfterTest
    public void quit(){
        driver.quit();
    }
}
