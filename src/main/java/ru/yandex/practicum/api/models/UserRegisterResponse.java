package ru.yandex.practicum.api.models;

public class UserRegisterResponse {
    private User user;

    public UserRegisterResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
