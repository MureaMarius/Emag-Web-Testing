package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.specificPages.LoginPage;
import singleton.WebDriverSingleton;
import utilities.SpecialData;

import java.util.ArrayList;
import java.util.List;

public class LoginPageTests {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeTest
    public void setUp(){
        driver = WebDriverSingleton.getInstance();

        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void openLoginPage(){
        driver.get(PagesDefinition.AUTHENTIFICATION_PAGE);
    }

    @Test(enabled = false)
    public void testLoginWithoutCredentials() throws InterruptedException {
        loginPage.login("", "", 0);

        Assert.assertTrue(driver.findElement(By.xpath(LoginElementsDefinition.MISSING_FIELD_PATH)).getText().
                equalsIgnoreCase(LoginElementsDefinition.MISSING_FIELD_MESSAGE));
    }

    @Test(enabled = false)
    public void testLoginWithCredentials() throws InterruptedException {
        loginPage.login(SpecialData.EMAIL, SpecialData.PASSWORD, 10000);
    }

    @Test(enabled = false)
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

        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));

        Thread.sleep(2000);
        Assert.assertTrue(driver.switchTo().window(browserTabs.get(1)).getCurrentUrl().equalsIgnoreCase(PagesDefinition.HELP_REDIRECT_PAGE));
    }

    @Test(enabled = false)
    public void testHomeRedirectPage() {
        loginPage.clickOnSpecificLoginButton("home button");

        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(PagesDefinition.HOME_PAGE));
    }

    @AfterTest
    public void quit(){
        driver.quit();
    }
}
