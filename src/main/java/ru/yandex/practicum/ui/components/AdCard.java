package ru.yandex.practicum.ui.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.practicum.ui.pages.EditAdPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class AdCard {

    private final SelenideElement card;
    private final SelenideElement titleElement;
    private final SelenideElement locationElement;
    private final SelenideElement priceElement;
    private final SelenideElement editButtonElement;

    private final By titleSelector = By.cssSelector(".about .h2");
    private final By locationSelector = By.cssSelector(".about .h3");
    private final By priceSelector = By.cssSelector(".price .h2");
    private final By editButtonSelector = By.cssSelector(".editButton");

    public AdCard(SelenideElement cardElement) {
        this.card = cardElement;
        this.titleElement = card.$(titleSelector);
        this.locationElement = card.$(locationSelector);
        this.priceElement = card.$(priceSelector);
        this.editButtonElement = card.$(editButtonSelector);
    }

    public String getTitle() {
        return titleElement.shouldBe(visible).getText();
    }

    public String getLocation() {
        return locationElement.shouldBe(visible).getText();
    }

    public String getPrice() {
        return priceElement.shouldBe(visible).getText();
    }

    public void clickEdit() {
        editButtonElement.shouldBe(exist).shouldBe(visible).click();
    }

    public EditAdPage editAd() {
        clickEdit();
        EditAdPage editAdPage = page(EditAdPage.class);
        return editAdPage;
    }

    // Методы для проверок
    public AdCard shouldHaveTitle(String expectedTitle) {
        titleElement.shouldHave(text(expectedTitle));
        return this;
    }

    public AdCard shouldHaveLocation(String expectedLocation) {
        locationElement.shouldHave(text(expectedLocation));
        return this;
    }

    public AdCard shouldHavePrice(int expectedPrice) {
        priceElement.shouldHave(text(expectedPrice + " ₽"));
        return this;
    }

    public AdCard shouldHaveEditButton() {
        if (!editButtonElement.exists()) {
            throw new AssertionError("Кнопка редактирования не найдена на карточке объявления");
        }
        editButtonElement.shouldBe(visible.because("Кнопка редактирования должна быть видима"));
        return this;
    }
}