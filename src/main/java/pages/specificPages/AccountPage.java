package pages.specificPages;

import model.User;
import org.openqa.selenium.By;
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

}
