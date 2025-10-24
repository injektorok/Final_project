package ru.yandex.practicum.ui.pages;

import ru.yandex.practicum.constants.Urls;
import ru.yandex.practicum.ui.components.Header;

import static com.codeborne.selenide.Selenide.open;

public class HomePage {

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
}