package ru.yandex.practicum.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.practicum.api.models.CreateAdRequest;
import ru.yandex.practicum.constants.Urls;
import ru.yandex.practicum.ui.components.Header;

import static com.codeborne.selenide.Selenide.*;

public class CreateAdPage {

    private final By nameInputSelector = By.cssSelector("input[name='name']");
    private final By descriptionTextareaSelector = By.cssSelector("textarea[name='description']");
    private final By priceInputSelector = By.cssSelector("input[name='price']");
    private final By publishButtonSelector = By.xpath("//button[text()='Опубликовать']");

    private final SelenideElement nameInput = $(nameInputSelector);
    private final SelenideElement descriptionTextarea = $(descriptionTextareaSelector);
    private final SelenideElement priceInput = $(priceInputSelector);
    private final SelenideElement publishButton = $(publishButtonSelector);

    private Header header;

    public CreateAdPage() {
        this.header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    public CreateAdPage openPage() {
        open(Urls.CREATE_AD_PAGE_URL);
        return this;
    }

    public void setName(String name) {
        nameInput.setValue(name);
    }

    public void setDescription(String description) {
        descriptionTextarea.setValue(description);
    }

    public void setPrice(int price) {
        priceInput.setValue(String.valueOf(price));
    }

    public void clickPublishButton() {
        publishButton.click();
    }

    public HomePage createAd(CreateAdRequest ad) {
        setName(ad.getName() + ad.getPrice());
        setDescription(ad.getDescription());
        setPrice(ad.getPrice());
        clickPublishButton();
        HomePage homepage = page(HomePage.class);
        return homepage;
    }
}