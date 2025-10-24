package ru.yandex.practicum.ui.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.practicum.ui.pages.CreateAdPage;
import ru.yandex.practicum.ui.pages.HomePage;
import ru.yandex.practicum.ui.pages.LoginPage;
import ru.yandex.practicum.ui.pages.ProfilePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Header {

    private final By loginAndRegisterButtonSelector = By.xpath("//button[text()='Вход и регистрация']");
    private final By profileButtonSelector = By.cssSelector("button.circleSmall");
    private final By userNameSelector = By.cssSelector(".profileText.name");
    private final By logoutButtonSelector = By.xpath("//button[text()='Выйти']");
    private final By createAdButtonSelector = By.xpath("//button[text()='Разместить объявление']");

    private final SelenideElement loginAndRegisterButton = $(loginAndRegisterButtonSelector);
    private final SelenideElement profileButton = $(profileButtonSelector);
    private final SelenideElement userNameLabel = $(userNameSelector);
    private final SelenideElement logoutButton = $(logoutButtonSelector);
    private final SelenideElement createAdButton = $(createAdButtonSelector);

    public String getUserName() {
        return userNameLabel.getText();
    }

    public LoginPage clickLoginAndRegisterButton() {
        loginAndRegisterButton.click();
        LoginPage loginPage = page(LoginPage.class);
        return loginPage;
    }

    public ProfilePage clickProfileButton() {
        profileButton.click();
        ProfilePage profilePage = page(ProfilePage.class);
        return profilePage;
    }

    public HomePage clickLogoutButton() {
        logoutButton.click();
        HomePage homePage = page(HomePage.class);
        return homePage;
    }

    public CreateAdPage clickCreateAdButton() {
        createAdButton.click();
        CreateAdPage createAdPage = page(CreateAdPage.class);
        return createAdPage;
    }

    public Header shouldBeAuthorized() {
        profileButton.shouldBe(visible);
        userNameLabel.shouldBe(visible);
        logoutButton.shouldBe(visible);
        return this;
    }

    public Header shouldNotBeAuthorized() {
        loginAndRegisterButton.shouldBe(visible);
        profileButton.shouldNotBe(visible);
        userNameLabel.shouldNotBe(visible);
        logoutButton.shouldNotBe(visible);
        return this;
    }
}
