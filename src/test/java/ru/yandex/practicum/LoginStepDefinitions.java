package ru.yandex.practicum;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {

    private final TestContext context;

    public LoginStepDefinitions(TestContext context) {
        this.context = context;
    }

    @When("Пользователь открывает страницу логина")
    public void openLoginPage() {
        context.loginPage = context.loginPage.openPage();
    }

    @When("Вводит валидные учетные данные")
    public void enterValidCredentials() {
        context.loginPage.loginUser(context.createdUser);
    }

    @When("Выполняет авторизацию")
    public void performLogin() {
        context.loginPage.clickLoginButton();
    }

    @Then("Пользователь успешно авторизован")
    public void userSuccessfullyLoggedIn() {
        context.homePage.getHeader().shouldBeAuthorized();
    }
}