package ru.yandex.practicum.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.practicum.constants.Urls;
import ru.yandex.practicum.ui.components.Header;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EditAdPage {

    private final By titleSelector = By.cssSelector(".createListing_title__IFtFs");
    private final By deleteButtonSelector = By.xpath("//button[text() = \"Удалить\"]");

    private final SelenideElement titleLabel = $(titleSelector);
    private final SelenideElement deleteButtonElement = $(deleteButtonSelector);

    private Header header;

    public EditAdPage() {
        this.header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    public EditAdPage openPage() {
        open(Urls.EDIT_AD_PAGE_URL);
        return this;
    }

    public EditAdPage shouldHaveTitle(String expectedTitle) {
        titleLabel.shouldBe(visible).shouldHave(text(expectedTitle));
        return this;
    }

    public EditAdPage shouldHaveDeleteButton() {
        if (!deleteButtonElement.exists()) {
            throw new AssertionError("Кнопка удаления не найдена на карточке объявления");
        }
        deleteButtonElement.shouldBe(visible.because("Кнопка удаления должна быть видима"));
        return this;
    }
}