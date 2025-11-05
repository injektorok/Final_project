package ru.yandex.practicum.ui.pages;

import ru.yandex.practicum.constants.Urls;
import ru.yandex.practicum.ui.components.Header;

import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

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
}