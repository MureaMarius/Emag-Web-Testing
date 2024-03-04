package pages.specificPages;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    WebDriver driver;

    private final By myAccountButton = By.id("my_account");
    private final By nameDetailPath = By.xpath("//span[@class='field-value js-personal-details-name']");
    private final By emailDetailPath = By.xpath("//span[@class='field-value']");
    private final By phoneNumberDetailPath = By.xpath("//span[@class='font-bold']");

    public AccountPage (WebDriver webDriver){
        this.driver = webDriver;
    }

    public void myAccountPage() {
        driver.findElement(myAccountButton).click();
    }

    public User getCurrentUserInfo(){
        String username = driver.findElement(nameDetailPath).getText();
        String email = driver.findElement(emailDetailPath).getText();
        String phoneNumber = driver.findElement(phoneNumberDetailPath).getText();

        return new User(email, username, phoneNumber);
    }
}
