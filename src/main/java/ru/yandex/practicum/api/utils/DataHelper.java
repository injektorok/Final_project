package ru.yandex.practicum.api.utils;

import com.github.javafaker.Faker;
import ru.yandex.practicum.api.models.CreateAdRequest;
import ru.yandex.practicum.api.models.UserRegisterRequest;

import java.util.Locale;

public class DataHelper {
    private final Faker faker;

    public DataHelper() {
        this.faker = new Faker(new Locale("en"));
    }

    public UserRegisterRequest createRandomUser() {
        String email = generateEmail();
        String password = generatePassword();
        String submitPassword = password;
        return new UserRegisterRequest(email, password, submitPassword);
    }

    public CreateAdRequest createRandomAd() {
        String productName = generateProductName();
        String category = "Авто";
        String condition = "Новый";
        String city = "Москва";
        String description = generateDescription();
        int price = generatePrice();
        return new CreateAdRequest(productName, category, condition, city, description, price);
    }

    private String generateEmail() {
        return faker.internet().emailAddress();
    }

    private String generatePassword() {
        return faker.internet().password(8, 12, true, true, true);
    }

    private String generateProductName() {
        return faker.commerce().productName();
    }

    private String generateDescription() {
        return faker.lorem().paragraph(2);
    }

    private int generatePrice() {
        return faker.number().numberBetween(100, 100000);
    }
}
