package ru.yandex.practicum.ui.pages;

import org.openqa.selenium.By;
import ru.yandex.practicum.constants.Urls;
import ru.yandex.practicum.ui.components.AdCard;
import ru.yandex.practicum.ui.components.Header;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private final By findAdInputSelector = By.xpath("//input[contains(@class, 'input_inputStandart') and @placeholder = 'Я хочу купить...']");
    private final By applyButtonSelector = By.xpath("//button[text() = 'Применить']");
    private final By advertisementsSelector = By.cssSelector(".card");
    private final By firstCardSelector = By.xpath("//div[@class = \"card\"][1]");

    private Header header;

    public HomePage() {
        this.header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    public HomePage openPage() {
        open(Urls.HOME_PAGE_URL);
        return this;
    }

    public List<AdCard> getAllAdvertisements() {
        return $$(advertisementsSelector).stream()
                .map(AdCard::new)
                .collect(Collectors.toList());
    }

    public HomePage shouldHaveAdvertisements() {
        // Ждем появления хотя бы одного объявления
        $(advertisementsSelector).shouldBe(visible);
        return this;
    }

    public HomePage findCreatedAdvertisement(String name) {
        $(findAdInputSelector).sendKeys(name);
        $(applyButtonSelector).click();

        return this;
    }

    public EditAdPage clickCard() {
        $(firstCardSelector).click();
        EditAdPage editAd = page(EditAdPage.class);
        return editAd;
    }
}