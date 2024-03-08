package pages;

import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.definitions.PagesDefinition;
import pages.specificPages.AccountPage;
import pages.specificPages.AuthentificationPage;
import singleton.WebDriverSingleton;
import utilities.Functions;
import utilities.SpecialData;

public class MyAccountPageTests {
    WebDriver driver;
    AuthentificationPage loginPage;
    AccountPage accountPage;

    @BeforeTest
    public void setUp() throws InterruptedException {
        driver = WebDriverSingleton.getInstance();

        loginPage = new AuthentificationPage(driver);
        accountPage = new AccountPage(driver);

        driver.get(PagesDefinition.AUTHENTIFICATION_PAGE);
        SpecialData specialData = new SpecialData();
        loginPage.login(specialData.getEmail(), specialData.getPassword(), 10000);
    }

    @BeforeMethod
    public void openLoginPage() {
        driver.get(PagesDefinition.MY_ACCOUNT_PAGE);

        if (!accountPage.closeSpot()) {
            System.out.println("Spot doesn't exists!");
        }
    }

    @Test(enabled = false)
    public void verifyAccountPageUrl(){
        accountPage.myAccountPage();

        Assert.assertTrue(driver.getCurrentUrl().contains(PagesDefinition.MY_ACCOUNT_PAGE),
                "Not expected current page. The current page should be: " + PagesDefinition.MY_ACCOUNT_PAGE +
                " but found: " + driver.getCurrentUrl());
    }

    @Test(enabled = false)
    public void verifyAccountDetailInfo() {
        User currentUser = accountPage.getCurrentUserInfo();
        SpecialData specialData = new SpecialData();

        Assert.assertEquals(currentUser.getName(), specialData.getName(),
                "Not expected name for the user account. The current user name should be: " + specialData.getName() +
                ", but found: " + currentUser.getName());

        Assert.assertEquals(currentUser.getEmail(), specialData.getEmail(),
                "Not expected email for the user account. The current user email should be: " + currentUser.getEmail() +
                        ", but found: " + specialData.getEmail());

        Assert.assertEquals(currentUser.getPhoneNumber(), specialData.getPhoneNumber(),
                "Not expected phone number for the user account. The current user phone number should be: " + specialData.getPhoneNumber() +
                        ", but found: " + currentUser.getPhoneNumber());
    }

    @Test(enabled = false)
    public void verifyAcronymName() {
        SpecialData specialData = new SpecialData();

        String actualAcronym = accountPage.getAcronymFromMyAccountPage();
        String expectedAcronym = new Functions().parseNameImage(specialData.getName());

        Assert.assertEquals(actualAcronym, expectedAcronym,
                "Not expected acronym for the user account. The current acronym should be: " + expectedAcronym +
                        ", but found: " + actualAcronym);
    }

    @Test(enabled = false)
    public void verifyRedirectToShoppingPage(){
        accountPage.redirectToShoppingPage();

        Assert.assertTrue(driver.getCurrentUrl().contains(PagesDefinition.SHOPPING_PAGE),
                "Not expected page after clicking on Shopping page button. Current page is: " +
                driver.getCurrentUrl());
    }

    @Test(enabled = false)
    public void verifyRedirectToVouchersPage(){
        accountPage.redirectToVouchersPage();

        Assert.assertTrue(driver.getCurrentUrl().contains(PagesDefinition.VOUCHERS_PAGE),
                "Not expected page after clicking on Vouchers page button. Current page is: " +
                        driver.getCurrentUrl());
    }

    @Test(enabled = false)
    public void verifyRedirectToMyWalletPage(){
        accountPage.redirectToMyWalletPage();

        Assert.assertTrue(driver.getCurrentUrl().contains(PagesDefinition.WALLET_PAGE),
                "Not expected page after clicking on My Wallet page button. Current page is: " +
                        driver.getCurrentUrl());
    }

    @Test
    public void verifySupportOption() throws InterruptedException {
        Assert.assertTrue(accountPage.showSupportOption(), "Not expected behaviour. A support chat should " +
                "be visible.");

        Assert.assertTrue(accountPage.hideSupportOption(), "Not expected behaviour. Support chat should be minimize.");
    }
}
