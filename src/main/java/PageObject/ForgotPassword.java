package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPassword {

    private final WebDriver driver;
    private final By loginButton = By.xpath(".//div/p/a");

    public ForgotPassword(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton(){
        waitMeMyFriend();
        driver.findElement(loginButton).click();
    }

    public void waitMeMyFriend()  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
