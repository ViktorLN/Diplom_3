package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final By loginPageButton = By.xpath(".//nav/a");
    private final By loginAccountButton = By.xpath(".//section[2]/div/button");

    private final By firstBun = By.xpath(".//ul[1]/a[1]/img");
    private final By firstSauce = By.xpath(".//ul[2]/a[1]/img");
    private final By firstFilling = By.xpath(".//ul[3]/a[1]/img");

    private final By bunChapter = By.xpath(".//main/section[1]/div[1]/div[1]");
    private final By sauceChapter = By.xpath(".//main/section[1]/div[1]/div[2]");
    private final By fillingChapter = By.xpath(".//main/section[1]/div[1]/div[3]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginPage (){
        waitMeMyFriend();
        driver.findElement(loginPageButton).click();
    }
    public void clickLoginAccount (){
        waitMeMyFriend();
        driver.findElement(loginAccountButton).click();
    }

    public boolean checkVisibleFirstBun(){
        waitMeMyFriend();
        return driver.findElement(firstBun).isDisplayed();
    }
    public boolean checkVisibleFirstSauce(){
        waitMeMyFriend();
        return driver.findElement(firstSauce).isDisplayed();
    }

    public boolean checkVisibleFirstFilling(){
        waitMeMyFriend();
        return driver.findElement(firstFilling).isDisplayed();
    }


    public void clickBunChapter(){
        waitMeMyFriend();
        driver.findElement(bunChapter).click();
    }

    public void clickSauceChapter(){
        waitMeMyFriend();
        driver.findElement(sauceChapter).click();
    }

    public void clickFillingChapter(){
        waitMeMyFriend();
        driver.findElement(fillingChapter).click();
    }



    public void waitMeMyFriend()  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
