package ru.yandex.practicum;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import ru.yandex.practicum.api.utils.DataHelper;
import ru.yandex.practicum.ui.components.Header;

public class RegistrationStepDefinitions {
    private final TestContext context;
    private final DataHelper dataHelper;

    public RegistrationStepDefinitions(TestContext context) {
        this.context = context;
        this.dataHelper = new DataHelper();
    }

    @Given("Сгенерированы тестовые данные пользователя")
    public void generateTestUserData() {
        context.createdUser = dataHelper.createRandomUser();
    }

    @Given("Пользователь уже зарегистрирован в системе")
    public void userAlreadyRegistered() {
        context.createdUser = dataHelper.createRandomUser();
        context.authClient.registerUser(context.createdUser);
    }

    @When("Пользователь открывает главную страницу")
    public void openHomePage() {
        context.homePage = context.homePage.openPage();
    }

    @When("Переходит на форму регистрации")
    public void navigateToRegistrationForm() {
        context.registrationPage = context.homePage.getHeader()
                .clickLoginAndRegisterButton()
                .clickRegisterButton();
    }

    @When("Регистрируется с валидными данными")
    public void registerWithValidData() {
        context.registrationPage.registerUser(context.createdUser);
    }

    @When("Пользователь пытается зарегистрироваться с теми же данными")
    public void tryRegisterWithSameData() {
        context.registrationPage = context.registrationPage
                .tryRegisterUser(context.createdUser);
    }

    @Then("Имя авторизованного пользователя отображается в хедере")
    public void userNameDisplayedInHeader() {
        Header header = context.homePage.getHeader();
        header.shouldBeAuthorized();
        Assertions.assertEquals("User.", header.getUserName(),
                "Дефолтное имя пользователя не совпадает");
    }

    @Then("Отображается ошибка регистрации")
    public void registrationErrorDisplayed() {
        context.registrationPage.shouldBeFailedRegistration();
    }
}