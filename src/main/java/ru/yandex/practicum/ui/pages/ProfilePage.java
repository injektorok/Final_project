package ru.yandex.practicum.ui.pages;

import org.openqa.selenium.By;
import ru.yandex.practicum.constants.Urls;
import ru.yandex.practicum.ui.components.AdCard;
import ru.yandex.practicum.ui.components.Header;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private final By advertisementsSelector = By.cssSelector(".card");

    private Header header;

    public ProfilePage() {
        this.header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    public ProfilePage openPage() {
        open(Urls.PROFILE_PAGE_URL);
        return this;
    }

    public List<AdCard> getAllAdvertisements() {
        return $$(advertisementsSelector).stream()
                .map(AdCard::new)
                .collect(Collectors.toList());
    }

    public ProfilePage shouldHaveAdvertisements() {
        // Ждем появления хотя бы одного объявления
        $(advertisementsSelector).shouldBe(visible);
        return this;
    }
}