package ru.yandex.practicum.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.practicum.api.models.UserRegisterRequest;
import ru.yandex.practicum.constants.Urls;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final By emailInputSelector = By.xpath("//input[@name='email']");
    private final By passwordInputSelector = By.xpath("//input[@name='password']");
    private final By submitPasswordInputSelector = By.xpath("//input[@name='submitPassword']");
    private final By createAccountButtonSelector = By.xpath("//button[text()='Создать аккаунт']");

    private final By registrationErrorSelector = By.xpath("//span[text()='Ошибка']");

    private final SelenideElement emailInput = $(emailInputSelector);
    private final SelenideElement passwordInput = $(passwordInputSelector);
    private final SelenideElement submitPasswordInput = $(submitPasswordInputSelector);
    private final SelenideElement createAccountButton = $(createAccountButtonSelector);

    private final SelenideElement registrationErrorLabel = $(registrationErrorSelector);

    public RegistrationPage openPage() {
        open(Urls.REGISTER_PAGE_URL);
        return this;
    }

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public void setSubmitPassword(String submitPassword) {
        submitPasswordInput.setValue(submitPassword);
    }

    public void clickCreateAccountButton() {
        createAccountButton.click();
    }

    public HomePage registerUser(UserRegisterRequest user) {
        fillInRegistrationData(user);
        HomePage homepage = page(HomePage.class);
        return homepage;
    }

    public RegistrationPage tryRegisterUser(UserRegisterRequest user){
        fillInRegistrationData(user);
        return this;
    }

    public RegistrationPage shouldBeFailedRegistration() {
        registrationErrorLabel.shouldBe(visible);
        return this;
    }

    private void fillInRegistrationData(UserRegisterRequest user){
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setSubmitPassword(user.getSubmitPassword());
        clickCreateAccountButton();
    }
}