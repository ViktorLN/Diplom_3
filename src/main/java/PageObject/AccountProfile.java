package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountProfile {

    private final WebDriver driver;
    private final By emailField = By.xpath(".//li[2]/div/div/input");
    private final By exitButton = By.xpath(".//li[3]/button");

    public AccountProfile(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkEmailField(){
        waitMeMyFriend();
        return driver.findElement(emailField).isDisplayed();
    }

    public void clickExitButton(){
        waitMeMyFriend();
        driver.findElement(exitButton).click();
    }

    public void waitMeMyFriend()  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
