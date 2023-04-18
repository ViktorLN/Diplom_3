import PageObject.ForgotPassword;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegistrationPage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import static io.restassured.RestAssured.given;

public class LoginTest {
    private WebDriver driver;
    private String myToken;

    @Before
    public void initialize(){
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";

        String json = "{\"email\": \"levkin_166@gmail.com\", \"password\": \"123456\", \"name\": \"Viktor\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post("api/auth/register");
        UserInfo userInfo;
        userInfo = response.as(UserInfo.class);
        //Сохраняем токен, для удаления пользовтеля после завершения тестов
        myToken = userInfo.getAccessToken().substring(7);
    }

    @Test
    public void loginWithMyAccountButtonTest(){
        String expected = "Выход";
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickLoginAccount();
        loginPage.login();
        mainPage.clickLoginPage();
        String actual = loginPage.checkExitButton();
        Assert.assertEquals("Логин не выполнен",expected, actual);
    }

    @Test
    public void loginWithLoginButtonTest(){
        String expected = "Выход";
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickLoginPage();
        loginPage.login();
        mainPage.clickLoginPage();
        String actual = loginPage.checkExitButton();
        Assert.assertEquals("Логин не выполнен",expected, actual);
    }

    @Test
    public void loginWithLoginButtonInRegistrationPageTest(){
        String expected = "Выход";
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        mainPage.clickLoginPage();
        loginPage.clickRegistrationPage();
        registrationPage.clickLoginButton();
        loginPage.login();
        mainPage.clickLoginPage();
        String actual = loginPage.checkExitButton();
        Assert.assertEquals("Логин не выполнен",expected, actual);
    }

    @Test
    public void loginWithLoginButtonInFoggotPasswordPageTest(){
        String expected = "Выход";
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPassword forgotPassword = new ForgotPassword(driver);
        mainPage.clickLoginAccount();
        loginPage.clickPasswordRecoveryButton();
        forgotPassword.clickLoginButton();
        loginPage.login();
        mainPage.clickLoginPage();
        String actual = loginPage.checkExitButton();
        Assert.assertEquals("Логин не выполнен",expected, actual);
    }

    @After
    public void teardown() {
        given()
                .auth().oauth2(myToken)
                .delete("api/auth/user");
        // Закрытие браузера
        driver.quit();
    }

}
