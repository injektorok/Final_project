package ru.yandex.practicum;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Timeout;
import ru.yandex.practicum.api.utils.DataHelper;
import ru.yandex.practicum.ui.components.AdCard;
import ru.yandex.practicum.ui.pages.EditAdPage;
import ru.yandex.practicum.ui.pages.HomePage;

import java.util.List;

public class AdvertisementStepDefinitions {

    private final TestContext context;
    private final DataHelper dataHelper;

    public AdvertisementStepDefinitions(TestContext context) {
        this.context = context;
        this.dataHelper = new DataHelper();
    }

    @Given("Пользователь авторизован в системе")
    public void userIsLoggedIn() {
        context.homePage = context.loginPage.openPage()
                .loginUser(context.createdUser);
        context.homePage.getHeader()
                .shouldBeAuthorized();
    }

    @Given("Пользователь имеет созданное объявление")
    public void userHasCreatedAd() {
        context.createdAd = dataHelper.createRandomAd();
        context.createAdPage = context.homePage.getHeader()
                .clickCreateAdButton();
        context.homePage = context.createAdPage.createAd(context.createdAd);
    }

    @When("Пользователь создает новое объявление")
    public void userCreatesNewAd() {
        context.createdAd = dataHelper.createRandomAd();
        context.createAdPage = context.homePage.getHeader()
                .clickCreateAdButton();
        context.createAdPage.createAd(context.createdAd);
    }

    @When("Пользователь редактирует объявление")
    public void userEditsAd() {
        HomePage homePage = context.homePage.getHeader()
                .shouldBeAuthorized().clickMainButton();
        homePage.shouldHaveAdvertisements();

        // поиск созданного объявления
        homePage.findCreatedAdvertisement(context.createdAd.getName() + context.createdAd.getPrice(), context.createdAd.getPrice());
        homePage.shouldHaveAdvertisements();

        List<AdCard> filteredList = homePage.getAllAdvertisements();
        Assertions.assertEquals(1, filteredList.size(), "Количество найденных объявлений не уникально!");

        AdCard firstAd = homePage.getAllAdvertisements().get(0);
        firstAd.shouldHaveEditButton();
        context.editAdPage = firstAd.editAd();
    }

    @When("Пользователь просматривает свои объявления")
    public void userViewsAds() {
        HomePage homePage = context.homePage.getHeader()
                .shouldBeAuthorized().clickMainButton();
        homePage.shouldHaveAdvertisements();

        // поиск созданного объявления
        homePage.findCreatedAdvertisement(context.createdAd.getName() + context.createdAd.getPrice(), context.createdAd.getPrice());
        
        List<AdCard> filteredList = homePage.getAllAdvertisements();
        Assertions.assertEquals(1, filteredList.size(), "Количество найденных объявлений не уникально!");
    }

    @Then("Объявление отображается в его профиле")
    public void adDisplayedInProfile() {
        context.homePage.shouldHaveAdvertisements();
    }

    @Then("Форма редактирования объявления открывается")
    public void editAdFormOpens() {
        context.editAdPage.shouldHaveTitle("Редактировать объявление");
    }

    @Then("У объявления доступна кнопка удаления")
    public void deleteButtonAvailable() {
        // проверка кнопки удаления
        EditAdPage editAd = context.homePage.clickCard();
        editAd.shouldHaveDeleteButton();
    }
}