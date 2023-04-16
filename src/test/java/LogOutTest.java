import PageObject.AccountProfile;
import PageObject.LoginPage;
import PageObject.MainPage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import static io.restassured.RestAssured.given;

public class LogOutTest {

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
        myToken = userInfo.getAccessToken().substring(7, userInfo.getAccessToken().length());
    }

    @Test
    public void checkLogoutInAccountProfilePage(){
        boolean expected = true;
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountProfile accountProfile = new AccountProfile(driver);
        mainPage.clickLoginAccount();
        loginPage.login();
        mainPage.clickLoginPage();
        accountProfile.clickExitButton();
        boolean actual = loginPage.checkLoginButton();
        Assert.assertEquals("Пользователь не разлогинился",expected, actual);
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
