package ru.yandex.practicum.api.models;

public class CreateAdRequest {
    private String name;
    private String category;
    private String condition;
    private String city;
    private String description;
    private int price;

    public CreateAdRequest(String name, String category, String condition, String city, String description, int price) {
        this.name = name;
        this.category = category;
        this.condition = condition;
        this.city = city;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
