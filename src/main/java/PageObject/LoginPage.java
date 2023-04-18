package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By registrationPageButton = By.xpath(".//p[1]/a");
    private final By loginButton = By.xpath(".//form/button");
    private final By passwordRecoveryButton = By.xpath(".//p[2]/a");
    private final By exitButton = By.xpath(".//li[3]/button");
    private final By emailField = By.xpath(".//fieldset[1]/div/div/input");
    private final By passwordField = By.xpath(".//fieldset[2]/div/div/input");

    private final By constructorButton = By.xpath(".//nav/ul/li[1]/a");
    private final By logoButton = By.xpath(".//nav/div");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegistrationPage (){
        waitMeMyFriend();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(registrationPageButton));
        driver.findElement(registrationPageButton).click();
    }

    public void login(){
        waitMeMyFriend();
        driver.findElement(emailField).sendKeys("levkin_166@gmail.com");
        driver.findElement(passwordField).sendKeys("123456");
        driver.findElement(loginButton).click();
    }

    public String checkExitButton(){
        waitMeMyFriend();
        return driver.findElement(exitButton).getText();
    }

    public void clickPasswordRecoveryButton(){
        driver.findElement(passwordRecoveryButton).click();
    }
    public boolean checkLoginButton(){
        waitMeMyFriend();
        return driver.findElement(loginButton).isDisplayed();
    }

    public void clickLogo(){
        waitMeMyFriend();
        driver.findElement(logoButton).click();
    }
    public void clickConstructor(){
        waitMeMyFriend();
        driver.findElement(constructorButton).click();
    }


    public void waitMeMyFriend()  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
