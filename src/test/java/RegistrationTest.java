import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegistrationPage;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import static io.restassured.RestAssured.given;


public class RegistrationTest {
    private WebDriver driver;
    private String myToken;



    @Before
    public void initialize(){
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    @Test
    public void checkRegistrationWithTrueDataGetExitButtonVisibleTest(){
        String expected = "Выход";
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickLoginPage();
        loginPage.clickRegistrationPage();
        registrationPage.registrationNewUser();
        loginPage.login();
        mainPage.clickLoginPage();
        String actual = loginPage.checkExitButton();
        Assert.assertEquals("Регистрация не выполнена выполнен",expected, actual);

        // Удаляем пользователя
        UserInfo userInfo =
                given()
                        .header("Content-type", "application/json")
                        .body("{\"email\": \"levkin_166@gmail.com\", \"password\": \"123456\"}")
                        .post("api/auth/login").as(UserInfo.class);


        myToken = userInfo.getAccessToken().substring(7, userInfo.getAccessToken().length());
        given()
                .auth().oauth2(myToken)
                .delete("api/auth/user");
    }

    @Test
    public void checkRegistrationWithShortPasswordGetErrorMessageText(){
        String expected = "Некорректный пароль";
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickLoginPage();
        loginPage.clickRegistrationPage();
        String actual = registrationPage.checkIncorrectPasswordMessage();
        Assert.assertEquals("Не сработала проверка пароля",expected, actual);
    }

    @After
    public void teardown() {
        // Закрытие браузера
        driver.quit();
    }
}
