package pages.specificPages;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.definitions.AccountPageDefinition;

import java.time.Duration;

public class AccountPage {
    WebDriver driver;

    private final By myAccountButton = By.id(AccountPageDefinition.MY_ACCOUNT_BUTTON);
    private final By nameDetailPath = By.xpath(AccountPageDefinition.NAME_DETAIL_PATH);
    private final By emailDetailPath = By.xpath(AccountPageDefinition.EMAIL_DETAIL_PATH);
    private final By phoneNumberDetailPath = By.xpath(AccountPageDefinition.PHONE_DETAIL_DETAIL_PATH);
    private final By acronymPath = By.xpath(AccountPageDefinition.ACRONYM_PATH);
    private final By shoppingPath = By.xpath(AccountPageDefinition.SHOPPING_PATH);
    private final By vouchersPath = By.xpath(AccountPageDefinition.VOUCHERS_PATH);
    private final By myWalletPath = By.xpath(AccountPageDefinition.MY_WALLET_PATH);
    private final By supportPath = By.xpath(AccountPageDefinition.SUPPORT_PATH);
    private final By dialogShowPath = By.xpath(AccountPageDefinition.DIALOG_SHOW_CLASS);
    private final By dialogHidePath = By.xpath(AccountPageDefinition.DIALOG_HIDE_CLASS);
    private final By minimizeChatButton = By.xpath(AccountPageDefinition.MINIMIZE_CHAT);
    private final By spotPath = By.xpath(AccountPageDefinition.SPOT_PATH);
    private final By closeSpotButton = By.xpath(AccountPageDefinition.CLOSE_SPOT_BUTTON);

    public AccountPage (WebDriver webDriver){
        this.driver = webDriver;
    }

    public void myAccountPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountButton));

        driver.findElement(myAccountButton).click();
    }

    public User getCurrentUserInfo(){
        String username = driver.findElement(nameDetailPath).getText();
        String email = driver.findElement(emailDetailPath).getText();
        String phoneNumber = driver.findElement(phoneNumberDetailPath).getText();

        return new User(email, username, phoneNumber);
    }

    public String getAcronymFromMyAccountPage() {
        return driver.findElement(acronymPath).getText();
    }

    public void redirectToShoppingPage() {
        driver.findElement(shoppingPath).click();
    }

    public void redirectToVouchersPage() {
        driver.findElement(vouchersPath).click();
    }

    public void redirectToMyWalletPage() {
        driver.findElement(myWalletPath).click();
    }

    public boolean closeSpot() {
        try {
            driver.findElement(spotPath);
            return true;
        }
        catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean showSupportOption() throws InterruptedException {
        if(driver.findElement(supportPath).isDisplayed()){
            driver.findElement(supportPath).click();
        }

        //Thread.sleep(10000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dialogShowPath));

        return driver.findElement(dialogShowPath).isDisplayed();
    }

    public boolean hideSupportOption() throws InterruptedException {
        driver.findElement(supportPath).click();
        //Thread.sleep(10000);
        driver.findElement(minimizeChatButton).click();

        return driver.findElement(dialogHidePath).isDisplayed();
    }
}
