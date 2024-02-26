package pages.specificPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.LoginElementsDefinition;

public class LoginPage {
    WebDriver driver;

    private final By emailTextBox = By.id(LoginElementsDefinition.USER_LOGIN_EMAIL);
    private final By passwordTextBox = By.id(LoginElementsDefinition.USER_LOGIN_PASSWORD);
    private final By continuaButton = By.id(LoginElementsDefinition.CONTINUA_BUTTON);
    private final By facebookAccountButton = By.name(LoginElementsDefinition.FACEBOOK_ACCOUNT_BUTTON);
    private final By googleAccountButton = By.name(LoginElementsDefinition.GOOGLE_ACCOUNT_BUTTON);
    private final By appleAccountButton = By.name(LoginElementsDefinition.APPLE_ACCOUNT_BUTTON);
    private final By helpButton = By.xpath(LoginElementsDefinition.HELP_BUTTON);
    private final By homePageButton = By.xpath(LoginElementsDefinition.HOME_PAGE_BUTTON);

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void login(String email, String password, int miliseconds) throws InterruptedException {
        this.setEmail(email);
        this.clickContinua();

        if(!password.isEmpty()){
            Thread.sleep(miliseconds);

            this.setPassword(password);
            this.clickContinua();
        }
    }

    public void setEmail(String email) {
        driver.findElement(emailTextBox).sendKeys(email);
    }

    public void setPassword(String password){
        driver.findElement(passwordTextBox).sendKeys(password);
    }

    public void clickContinua(){
        driver.findElement(continuaButton).click();
    }

    public void clickOnSpecificLoginButton(String specificButton){
        switch (specificButton){
            case "facebook account":
                driver.findElement(facebookAccountButton).click();
                break;
            case "google account":
                driver.findElement(googleAccountButton).click();
                break;
            case "apple account":
                driver.findElement(appleAccountButton).click();
                break;
            case "home button":
                driver.findElement(homePageButton).click();
                break;
            case "help button":
                driver.findElement(helpButton).click();
                break;
            default:
                throw new IllegalStateException("Unexpected value for button type: " + specificButton);
        }
    }
}
