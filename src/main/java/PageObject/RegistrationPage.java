package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final By nameField = By.xpath(".//fieldset[1]/div/div/input");
    private final By emailField = By.xpath(".//fieldset[2]/div/div/input");
    private final By passwordField = By.xpath(".//fieldset[3]/div/div/input");
    private final By registrationAcceptButton = By.xpath(".//form/button");
    private final By incorrectPasswordField = By.xpath(".//fieldset[3]/div/p");
    private final By loginButton = By.xpath(".//div/div/p/a");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void registrationNewUser (){
        waitMeMyFriend();
        driver.findElement(nameField).sendKeys("Viktor");
        driver.findElement(emailField).sendKeys("levkin_166@gmail.com");
        driver.findElement(passwordField).sendKeys("123456");
        driver.findElement(registrationAcceptButton).click();
    }

    public String checkIncorrectPasswordMessage(){
        waitMeMyFriend();
        driver.findElement(nameField).sendKeys("Viktor");
        driver.findElement(emailField).sendKeys("levkin_166@gmail.com");
        driver.findElement(passwordField).sendKeys("123");
        driver.findElement(registrationAcceptButton).click();
        return driver.findElement(incorrectPasswordField).getText();
    }

    public void clickLoginButton(){
        waitMeMyFriend();
        driver.findElement(loginButton).click();
    }



    public void waitMeMyFriend()  {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
