import PageObject.LoginPage;
import PageObject.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ConstructorTest {
    private WebDriver driver;


    @Before
    public void initialize(){
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void checkLogoGoToMainPage(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        boolean expected = true;
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickLoginPage();
        loginPage.clickLogo();
        boolean actual = mainPage.checkVisibleFirstBun();
        Assert.assertEquals("Переход не выполнен",expected, actual);
    }

    @Test
    public void checkConstructorGoToMainPage(){
        driver.get("https://stellarburgers.nomoreparties.site/");
        boolean expected = true;
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickLoginAccount();
        loginPage.clickConstructor();
        boolean actual = mainPage.checkVisibleFirstBun();
        Assert.assertEquals("Переход не выполнен",expected, actual);
    }

    @Test
    public void checkBunChapterTest(){
        boolean expected = true;
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceChapter();
        mainPage.clickBunChapter();
        boolean actual = mainPage.checkVisibleFirstBun();
        Assert.assertEquals("Переход в раздел не произошел",expected, actual);
    }

    @Test
    public void checkSauceChapterTest(){
        boolean expected = true;
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceChapter();
        boolean actual = mainPage.checkVisibleFirstSauce();
        Assert.assertEquals("Переход в раздел не произошел",expected, actual);
    }

    @Test
    public void checkFillingChapterTest(){
        boolean expected = true;
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingChapter();
        boolean actual = mainPage.checkVisibleFirstFilling();
        Assert.assertEquals("Переход в раздел не произошел",expected, actual);
    }

    @After
    public void teardown() {
        // Закрытие браузера
         driver.quit();
    }
}
