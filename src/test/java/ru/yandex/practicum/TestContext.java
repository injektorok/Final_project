package ru.yandex.practicum;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import ru.yandex.practicum.api.clients.AuthClient;
import ru.yandex.practicum.api.models.CreateAdRequest;
import ru.yandex.practicum.api.models.UserRegisterRequest;
import ru.yandex.practicum.api.utils.DataHelper;
import ru.yandex.practicum.constants.Urls;
import ru.yandex.practicum.ui.pages.*;

public class TestContext {

    public AuthClient authClient;
    public DataHelper dataHelper;
    public HomePage homePage;
    public LoginPage loginPage;
    public RegistrationPage registrationPage;
    public CreateAdPage createAdPage;
    public ProfilePage profilePage;
    public EditAdPage editAdPage;

    public UserRegisterRequest createdUser;
    public CreateAdRequest createdAd;
    public Response lastApiResponse;

    public TestContext() {
        setup();
    }

    private void setup() {
        configureDriver();
        this.authClient = new AuthClient(Urls.BASE_URI);
        this.dataHelper = new DataHelper();
        this.homePage = new HomePage();
        this.loginPage = new LoginPage();
        this.registrationPage = new RegistrationPage();
        this.createAdPage = new CreateAdPage();
        this.profilePage = new ProfilePage();
        this.editAdPage = new EditAdPage();
    }

    private void configureDriver() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.browserSize = "maximize";
        Configuration.headless = false;
    }

    public void registerUserViaApi(UserRegisterRequest user) {
        lastApiResponse = authClient.registerUser(user);
        lastApiResponse.then().statusCode(HttpStatus.SC_CREATED);
    }

    public void cleanup() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}