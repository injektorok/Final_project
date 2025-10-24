package ru.yandex.practicum.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.practicum.api.models.UserRegisterRequest;
import ru.yandex.practicum.constants.Urls;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final By emailInputSelector = By.cssSelector("input[name='email']");
    private final By passwordInputSelector = By.cssSelector("input[name='password']");
    private final By loginButtonSelector = By.xpath("//button[text()='Войти']");
    private final By registerButtonSelector = By.xpath("//button[text()='Нет аккаунта']");

    private final SelenideElement emailInput = $(emailInputSelector);
    private final SelenideElement passwordInput = $(passwordInputSelector);
    private final SelenideElement loginButton = $(loginButtonSelector);
    private final SelenideElement registerButton = $(registerButtonSelector);

    public LoginPage openPage() {
        open(Urls.LOGIN_PAGE_URL);
        return this;
    }

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public HomePage loginUser(UserRegisterRequest user){
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickLoginButton();
        HomePage homePage = page(HomePage.class);
        return homePage;
    }

    public RegistrationPage clickRegisterButton() {
        registerButton.click();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        return registrationPage;
    }
}